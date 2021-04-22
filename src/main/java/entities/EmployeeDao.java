package entities;

import javax.persistence.EntityManager;

public class EmployeeDao extends JpaDaoImpl {
    public EmployeeDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
