package com.exchange.client.util;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by xiangyutian on 15/12/20.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class SerializeUtils {
    public SerializeUtils() {
    }

    public static Object deserialization(String filePath) {
        ObjectInputStream in = null;

        Object var4;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            Object e = in.readObject();
            in.close();
            var4 = e;
        } catch (FileNotFoundException var13) {
            throw new RuntimeException("FileNotFoundException occurred. ", var13);
        } catch (ClassNotFoundException var14) {
            throw new RuntimeException("ClassNotFoundException occurred. ", var14);
        } catch (IOException var15) {
            throw new RuntimeException("IOException occurred. ", var15);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var12) {
                    throw new RuntimeException("IOException occurred. ", var12);
                }
            }
        }
        return var4;
    }

    public static void serialization(String filePath, Object obj) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException var12) {
            throw new RuntimeException("FileNotFoundException occurred. ", var12);
        } catch (IOException var13) {
            throw new RuntimeException("IOException occurred. ", var13);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var11) {
                    throw new RuntimeException("IOException occurred. ", var11);
                }
            }
        }
    }

    public static String getSerializableString(Serializable object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String productBase64 = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            productBase64 = new String(Base64.encode(baos.toByteArray(), 0));
        } catch (IOException var15) {
            LogUtils.e("SerializeUtils", var15.toString());
        } catch (OutOfMemoryError var16) {
            LogUtils.e("SerializeUtils", var16.toString());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException var14) {
                    LogUtils.e("SerializeUtils", var14.toString());
                }
            }

        }

        if (productBase64 == null) {
            productBase64 = "";
        }

        return productBase64;
    }

    public static Serializable getSerializableObject(String data) {
        byte[] objBytes = Base64.decode(data.getBytes(), 0);
        if (objBytes != null && objBytes.length != 0) {
            ByteArrayInputStream bi = null;
            ObjectInputStream oi = null;
            Object object = null;

            try {
                bi = new ByteArrayInputStream(objBytes);
                oi = new ObjectInputStream(bi);
                object = oi.readObject();
                return (Serializable) object;
            } catch (Exception var18) {
                ;
            } finally {
                if (oi != null) {
                    try {
                        oi.close();
                    } catch (IOException var17) {
                        ;
                    }
                }

                if (bi != null) {
                    try {
                        bi.close();
                    } catch (IOException var16) {
                        ;
                    }
                }
            }

            return null;
        } else {
            return null;
        }
    }
}
