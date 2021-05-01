package chess.dao;

import chess.dao.dto.ChessGame;
import chess.domain.piece.attribute.Color;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateChessDao implements ChessDao {
    private final RowMapper<ChessGame> chessGameRowMapper = (rs, rownum) -> {
        long gameId = rs.getLong("id");
        String nextTurn = rs.getString("next_turn");
        boolean running = rs.getBoolean("running");
        String pieces = rs.getString("pieces");
        String title = rs.getString("title");
        return new ChessGame(gameId, Color.of(nextTurn), running, pieces, title);
    };
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    public JdbcTemplateChessDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("chess_game")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(ChessGame entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pieces", entity.getPieces());
        parameters.put("running", entity.isRunning());
        parameters.put("next_turn", entity.getNextTurn());
        parameters.put("title", entity.getTitle());

        return insertAction.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public Optional<ChessGame> findById(long id) {
        String query =
                "SELECT * " +
                        "FROM chess_game " +
                        "WHERE id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(query, chessGameRowMapper, id));
    }


    @Override
    public void update(ChessGame entity) {
        String query =
                "UPDATE chess_game " +
                        "SET pieces = ?, running = ? , next_turn = ?" +
                        "WHERE id = ?";

        jdbcTemplate.update(query, entity.getPieces(), entity.isRunning(),
                entity.getNextTurn().name(), entity.getId());
    }

    @Override
    public List<ChessGame> findAllOnRunning() {
        String query =
                "SELECT * " +
                        "FROM chess_game " +
                        "WHERE running = ?";

        return jdbcTemplate.query(query, chessGameRowMapper, true);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM chess_game " +
                "WHERE id = ?";

        jdbcTemplate.update(query, id);
    }
}
