package com.company.item9;

import java.io.*;

public class TryWithResources {
    private static final int BUFFER_SIZE = 16;

    /*
        To be usable with this construct, a resource must implement the AutoCloseable interface,
        which consists of a single void-returning close method. Many classes and interfaces in the Java libraries
        and in third-party libraries now implement or extend AutoCloseable. If you write a class that
        represents a resource that must be closed, your class should implement AutoCloseable too.
     */
    public static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }
    // provide shorter, more readable code, far better diagnostics.

    // Always use try-with-resources in preference to try-finally when working with resources
    // that must be closed. The resulting code is shorter and clearer, and the exceptions that
    // it generates are more useful.
}
