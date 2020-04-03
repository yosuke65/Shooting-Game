
package controller;




public class Event {
    
    private final Subject subject;
    
    public Event(Subject subject) {

        this.subject = subject;
    }
    
    
    public Subject getSubject() {
        return subject;
    }
    
}

