package org.beinclusive.beinclusive.repository;

import java.util.List;

import org.beinclusive.beinclusive.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

    public List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);
}
