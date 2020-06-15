import com.kihcyaz.aynorb.beans.Teacher;
import com.kihcyaz.aynorb.util.BeanDefinition;
import com.kihcyaz.aynorb.util.BeanFactory;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        BeanDefinition teacherDefinition = new BeanDefinition();
        teacherDefinition.setBeanName("teacher");
        teacherDefinition.setClazz("com.kihcyaz.aynorb.beans.Teacher");
        teacherDefinition.setFactoryBean("teacherFactory");
        teacherDefinition.setFactoryMethod("getInstance");
        teacherDefinition.setScope("prototype");

        BeanDefinition factoryBean = new BeanDefinition();
        factoryBean.setBeanName("teacherFactory");
        factoryBean.setClazz("com.kihcyaz.aynorb.factory.TeacherFactory");

        ConcurrentHashMap<String, BeanDefinition> map = new ConcurrentHashMap<>();
        map.put(teacherDefinition.getBeanName(), teacherDefinition);
        map.put(factoryBean.getBeanName(), factoryBean);
        BeanFactory factory = new BeanFactory(map);

        Teacher teacher = (Teacher)factory.getBean("teacher");
        Teacher teacher1 = (Teacher)factory.getBean("teacher");
        System.out.println(teacher == teacher1);

    }
}
