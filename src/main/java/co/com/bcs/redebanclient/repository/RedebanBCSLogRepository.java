package co.com.bcs.redebanclient.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.bcs.redebanclient.entity.RedebanBCSService;

@Repository
public interface RedebanBCSLogRepository extends JpaRepository<RedebanBCSService, UUID> {

}
