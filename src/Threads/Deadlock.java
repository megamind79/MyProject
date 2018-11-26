package Threads;

class Resource {
    String resource;
    Resource(String resource) {
        this.resource = resource;
    }

    void useResource() throws InterruptedException {
        System.out.println(Thread.currentThread() + " is using resource "+resource);
        Thread.sleep(1000);
    }
}

class User implements Runnable {
    String user;
    Resource resource1, resource2;
    Thread thread;

    User(String user) {
        this.user = user;
        thread = new Thread(this, user);
    }

    void setResources(Resource resource1, Resource resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    void useResources() {
        try {
            synchronized (resource1) {
                resource1.useResource();
                synchronized (resource2) {
                    resource2.useResource();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        useResources();
    }

    void startUsing() {
        thread.start();
    }
}

public class Deadlock {
    public static void main(String[] args) throws Exception {
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");
        User user1 = new User("User1");
        User user2 = new User("User2");
        user1.setResources(resource1, resource2);
        user2.setResources(resource2, resource1);
        user1.startUsing();
        user2.startUsing();
    }
}
