package org.hammerhead226.masterfrcscouter.backend;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by Adi on 7/21/2015.
 */
public class DataRW {
    //public static String TAG = "DataRW";
    private static File hashMapSerializedFile = getHashMapSerializedFile();
    public static HashMap<String, File> filePathsMap = init();

    private static HashMap<String, File> init() {
        if (hashMapSerializedFile.exists()) {
            return (HashMap<String, File>) getObjectFromFile(hashMapSerializedFile);
        }
        return new HashMap<>();
    }

    private static File getHashMapSerializedFile() { return new File(Constants.getInternalDataDir().getAbsolutePath(), "filePathsMap.ser"); }

    public static boolean mapContains(String key) {
        return filePathsMap.containsKey(key);
    }

    public static void removeMapEntry(String key) {
        if (filePathsMap.containsKey(key)) {
            File f = filePathsMap.get(key);
            f = null;
            filePathsMap.put(key, null);
            filePathsMap.remove(key);
        }
    }

    public static void addMapEntry(String key, File file) {
        if (mapContains(key)) {
            removeMapEntry(key);
        }
        try {
            filePathsMap.put(key, file);
            FileOutputStream fileOutputStream = new FileOutputStream(hashMapSerializedFile);
            fileOutputStream.write(serialize(filePathsMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean writeStringAsFile(File file, String data, String key) {
        try {
            writeObjectAsFile(file, data, key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getStringFromFile(String key) {
        if (filePathsMap.containsKey(key)) {
            try {
                File file = filePathsMap.get(key);
                InputStream in = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                return out.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean writeObjectAsFile(File file, Object object, String key) {
        try {
            addMapEntry(key, file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(serialize(object));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object getObjectFromFile(String key) {
        try {
            File file = null;
            if (mapContains(key)) {
                file = filePathsMap.get(key);
            }
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[0xFFFF];
            for (int len; (len = is.read(buffer)) != -1; ) {
                os.write(buffer, 0, len);
            }
            os.flush();
            byte[] data = os.toByteArray();
            return deserialize(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getObjectFromFile(File file) {
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[0xFFFF];
            for (int len; (len = is.read(buffer)) != -1; ) {
                os.write(buffer, 0, len);
            }
            os.flush();
            byte[] data = os.toByteArray();
            return deserialize(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
