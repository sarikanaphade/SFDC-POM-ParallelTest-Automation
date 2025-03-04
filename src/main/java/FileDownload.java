import java.io.*;

public class FileDownload {
	public static String downloadPath = "";
	
	//This method is used to get the latest downloaded file from directory
	public static File getLatestFilefromDir(String dirPath) {
		
		File dir = new File(downloadPath);
		File[] files = dir.listFiles();
		if(files == null || files.length==0) {
			return null;
		}
		File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}

	//This method checks the extension of the file downloaded
	public static boolean isFileDownloaded_Extension(String dirPath, String extension) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] files = dir.listFiles();
		if(files == null || files.length==0) {
			flag = false;
		}
		for(int i=1; i<files.length; i++) {
			if(files[i].getName().contains(extension)) {
				flag = true;
			}
		}
		return flag;
	}

//This method is to verify if the file is downloaded or not
		public boolean isFileDownloaded(String downloadPath, String fileName) {
			boolean flag = false;
			File dir = new File(downloadPath);
			File[] dir_contents = dir.listFiles();
		
				for(int i=0; i<dir_contents.length; i++) {
					if(dir_contents[i].getName().equalsIgnoreCase(fileName)) {
					return flag = true;
				}
			}
			return flag;
		}
			
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
