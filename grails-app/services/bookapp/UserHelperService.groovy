package bookapp

import grails.transaction.Transactional

import java.util.concurrent.atomic.AtomicReference

@Transactional
class UserHelperService {
    static transactional = true
    private static AtomicReference<Long> currentTime =  new AtomicReference<>(System.currentTimeMillis());

    def serviceMethod() {
    }


    public static Long nextId() {
        Long prev;
        Long next = System.currentTimeMillis();


        for(;;){ // infinite for

            prev = currentTime.get()
            next = next > prev ? next : prev + 1

            if(currentTime.compareAndSet(prev, next) ){ //condition to break, oppossite to while
                break
            }
        }
        return next;
    }

}
