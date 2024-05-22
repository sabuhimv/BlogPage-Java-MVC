package org.example.blog.helpers;

public class SeoHelper {
    public String seoUrlHelper(String text){
        //Mənfi personajlar niyə bizi cəzb edir?

        String[] change =text.toLowerCase()
                .replaceAll("ə","e")
                .replaceAll("ç","c")
                .replaceAll("ş","s")
                .replaceAll("ö","o")
                .replaceAll("ü","u")
                .split(" ");
        String result = String.join("-",change);
        return result.replaceAll("[^a-z0-9-]","");
    }
}
