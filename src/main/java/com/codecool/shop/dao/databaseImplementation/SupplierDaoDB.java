package com.codecool.shop.dao.databaseImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {
    private final DataSource dataSource;
    private static SupplierDaoDB instance=null;
    public static SupplierDaoDB getInstance(DataSource dataSource){
        if(instance==null){
            instance=new SupplierDaoDB(dataSource);
        }
        return instance;

    }

    public SupplierDaoDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
