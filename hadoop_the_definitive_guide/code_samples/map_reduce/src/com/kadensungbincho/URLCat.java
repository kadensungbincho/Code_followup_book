package com.kadensungbincho;

import java.net.URL;

public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactor());
    }

    public static void main(String[] args) throws Exception {
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}

/*
export HADOOP_CLASSPATH=hadoop-exmpales.jar
hadoop URLCat hafs://localhost/user/tom/quangle.txt
 */
