/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;

/**
 * Represents a bundle of SQLData. The bundled data facilitates interactions
 * with the oracle SQL data base, by using {@link SQLData} to store necessary data.
 * <p>
 * This bundle can be used to send and receive data from an OracleSQL connection.
 * The data inserted must be in the order of arrival. For example, if one wanted to send
 * the numbers 52 and 100, alongsided with the string "SUM", the syntax should be:
 * <p>
 * addData(new SQLData(52,Integer.class));
 * addData(new SQLData(100,Integer.class));
 * addData(new SQLData("SUM",String.class));
 * @author G11
 */
public class SQLDataBundle
{
    /**
     * The dataset of SQLData.
     */
    private ArrayList<SQLData> dataset;
    
    /**
     * Creates an instance of {@link SQLDataBundle} with null parameters.
     */
    public SQLDataBundle()
    {
        dataset = new ArrayList();
    }

    /**
     * Adds a {@link SQLData} to this {@link SQLDataBundle}.
     * @param data ({@link SQLData}) The SQLData to add.
     * @return (boolean) True if the data was successfully added.
     */
    public boolean addData(SQLData data)
    {
        return dataset.add(data);
    }

    /**
     * Returns the {@link SQLData} at the specified index.
     * @param index (int) The target index.
     * @return ({@link SQLData}) The specified SQLData.
     */
    public SQLData getData(int index)
    {
        return dataset.get(index);
    }
    /**
     * Returns this data bundle's size.
     * @return (int) The size of the bundle.
     */
    public int size()
    {
        return dataset.size();
    }
}
