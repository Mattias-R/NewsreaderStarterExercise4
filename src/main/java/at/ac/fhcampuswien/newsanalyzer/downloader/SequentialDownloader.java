package at.ac.fhcampuswien.newsanalyzer.downloader;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) throws DownloaderException {
        long start = System.nanoTime();
        int count = 0;
        for (String url : urls) {
            String fileName = saveUrl2File(url);
            if(fileName != null)
                count++;
        }
        long end = System.nanoTime();
        System.out.println("Time: "+ (end - start)/1000000 + " milliseconds");
        return count;
    }
}
