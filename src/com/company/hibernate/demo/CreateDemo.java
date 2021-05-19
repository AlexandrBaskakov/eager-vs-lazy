package com.company.hibernate.demo;

import com.company.hibernate.entity.Instructor;
import com.company.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml") // its can be empty, search for default name
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

/*            // create the objects
            Instructor instructor =
                    new Instructor("Chad", "Darby", "chad@com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("faf.com/youtube", "Luv 2 code");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            // NOTE: this also save the details object because of CascadeType.ALL*/

            Instructor instructor =
                    new Instructor("Madhu", "Patel", "madu@com");

            InstructorDetail instructorDetail =
                    new InstructorDetail("maddddd.com/youtube", "Guitar");

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!!");
        }
        finally {
            factory.close();
        }
    }
}
