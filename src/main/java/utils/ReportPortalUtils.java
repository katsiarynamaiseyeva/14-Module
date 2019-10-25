package utils;

import com.epam.reportportal.message.ReportPortalMessage;
import reporting.Log;
import rp.com.google.common.io.ByteSource;

import java.io.File;
import java.io.IOException;

public class ReportPortalUtils {

  public static final int MAX_BYTES = 67108864;

  private ReportPortalUtils() {}

  public static void submitImage(byte[] bytes, String name) {
    submitData(bytes, "image/png", name);
  }

  public static void submitFile(File file, String name) {
    try {
      Log.info(new ReportPortalMessage(file, name));
    } catch (IOException e) {
      Log.error("IOException during submitFile ", e);
    }
  }

  public static void submitData(byte[] bytes, String mediaType, String name) {
    if (bytes.length >= MAX_BYTES) {
      Log.info("Cannot sent data to RP: maximum upload size of 67108864 bytes exceeded");
    }

    Log.info(new ReportPortalMessage(ByteSource.wrap(bytes), mediaType, name));
  }
}
