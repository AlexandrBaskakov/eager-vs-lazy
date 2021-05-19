package com.company.hibernate.demo;

import com.company.hibernate.entity.Course;
import com.company.hibernate.entity.Instructor;
import com.company.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml") // its can be empty, search for default name
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int id = 1;

            // get the instructor from db
            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id=:instructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("instructorId", id);

            // execute query and get instructor

            Instructor instructor = query.getSingleResult();

            System.out.println("luv2code: Instructor: " + instructor);

            //commit transaction
            session.getTransaction().commit();

            session.close();

            System.out.println("\nThe session is now closed!\n");

            System.out.println("luv2code: Courses: " + instructor.getCourses());

            System.out.println("luv2code: Done!!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
