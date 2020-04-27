package kiki.blog.backend;

public class Test {
    public static void main(String[] args){
        String str = "&%#dd&%#s";
        String[] s = str.split("&%#");
        System.out.println(s.length);
        System.out.println(s[0]);

    }
}
