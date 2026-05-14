/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author user
 */
public interface GenericoDAO<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(Integer id);

    List<T> findAll();
}
