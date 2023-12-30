package fr.alinedubois.mymeds;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContexteSpring implements ApplicationContextAware {

    private static ApplicationContext springApplicationContext;


    public static ApplicationContext recuperer() {
        return springApplicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springApplicationContext = applicationContext;
    }
}
