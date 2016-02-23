/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 * Represents data to be sent or received from SQL. It can house any kind of
 * object as long as there is a representation of it in the database.
 * <p>
 * The class has the object as a value and the class of the object.
 * @author G11
 */
public class SQLData
{
    private Object value;
    private Class clazz;
    
    /**
     * Creates an instance of {@link SQLData} with null parameters.
     */
    public SQLData()    
    {
    }
    
    /**
     * Creates an instance of {@link SQLData} with the specified parameters.
     * @param o (Object) The value to be stored in this {@link SQLData}.
     * @param clazz (Class) The class of the value.
     */
    public SQLData(Object o,Class clazz)
    {
        setValue(o);
        setClazz(clazz);
    }

    /**
     * Returns the class of this {@link SQLData}.
     * @return the class
     */
    public Class getClazz()
    {
        return clazz;
    }

    /**
     * Sets the value of this {@link SQLData}.
     * @param value (Object) The value to set
     */
    public void setValue(Object value)
    {
        this.value = value;
        setClazz(value.getClass());
    }

    /**
     * Sets the class of this {@link SQLData}.
     * <p>
     * The class should be equal to the object's value!
     * @param clazz the clazz to set
     */
    public void setClazz(Class clazz)
    {
        this.clazz = clazz;
    }

    /**
     * Returns the value of this {@link SQLData}.
     * @return (Object) The specified value.
     */
    public Object getValue()
    {
        return value;
    }
}
