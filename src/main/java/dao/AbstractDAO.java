package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */

/**
 * The AbstractDAO class is an abstract base class for Data Access Objects (DAOs) that provides common functionality
 * for performing CRUD operations on entities of type T.
 *
 * @param <T> the type of entity managed by the DAO
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    //afisat
    private final Class<T> type;
    /**
     * Constructs a new instance of the AbstractDAO class.
     * It determines the type of entity managed by the DAO based on the generic type parameter.
     */

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    //clasa poate fi de type client ord etc

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName()); //numele clasei, numele tabelului in clasa de date
        sb.append(" WHERE " + field + " =?"); //field=id si ? e placeholder inlocuit cu id ul pe care il dau eu
        return sb.toString();
    }
    //intr-un stringbuilder fac query ul

    private String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }
    //nu dau id ca am nev de toate
    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String myQuery = createFindAllQuery();
        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.prepareStatement(myQuery);
            resultSet = statement.executeQuery();//result set se salveaza toiate datele
            return createObjects(resultSet);//create obj create obiecte, lista de obiecte
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(resultSet);
        }
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>(); //din rand de tabele creaza un ob
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        int number = 0;
        for(Field field : type.getDeclaredFields())
        {
            if(!field.getName().equals("id")){//nu adauga id pt ca se autogen
                if(number>0){ //daca e 0 nu vreau sa pun virgula, number creste la fiecare field
                    sb.append(", ");
                }
                sb.append(field.getName());
                number++;
            }

        }
        sb.append(") VALUES ("); //ce valori sunt in field uri
        number=0;
        for(Field field : type.getDeclaredFields())
        {
            if(!field.getName().equals("id")){
                if(number>0){
                    sb.append(", ");
                }
                sb.append("?");
                number++;
            }

        }
        sb.append(")");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String myQuery = sb.toString();
        System.out.println(myQuery);
        int index=1;
        try { //parcurg fiecare field in for si daca nu e id atunci setez in baza de date la acel index valoarea din obiect
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(myQuery, Statement.RETURN_GENERATED_KEYS);
            for(Field field : type.getDeclaredFields()){
                field.setAccessible(true); //cls e private si incerc sa l accesez
                if(!field.getName().equals("id")){
                    statement.setObject(index,field.get(t));//se pune in tabel valoarea din field de pe poz aia
                    index++;
                }
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public T update(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int i=0;
        for(Field field: type.getDeclaredFields()){
            if(i>0){
                sb.append(", ");
            }
            sb.append(field.getName());
            sb.append("=?");
            i++;
        }
        sb.append(" WHERE id=?");
        Connection connection = null;
        PreparedStatement statement = null;
        String myQuery = sb.toString();
        System.out.println(myQuery);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(myQuery);
            int index = 1;
            for(Field field : type.getDeclaredFields()){
                    field.setAccessible(true);
                    statement.setObject(index,field.get(t));
                    index++;
            }
            Method idMethod = type.getMethod("getId");//apeleaza si returneaza id
            int id = (int) idMethod.invoke(t);
            statement.setInt(index,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
    public boolean delete(int id){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id=?");
        Connection connection = null;
        PreparedStatement statement = null;
        String myQuery = sb.toString();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(myQuery);
            statement.setInt(1,id);
            if(statement.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
