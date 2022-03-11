package repositoryDataTables;

import com.caterer.app.entity.Caterer;
import org.springframework.data.mongodb.datatables.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatererRepositoryDataTables extends DataTablesRepository<Caterer,String> {
}
