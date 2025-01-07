package com.velfox.utilitys;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScoreManager {

    private static final String HIGH_SCORE_FILE = "data/highscores.txt";
    private static final int MAX_HIGH_SCORES = 5;

    // Zorg ervoor dat de data-map bestaat
    static {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    public static List<HighScoreEntry> getHighScores() {
        List<HighScoreEntry> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highScores.add(new HighScoreEntry(name, score));
                }
            }
        } catch (IOException e) {
            // Als het bestand niet bestaat of een fout optreedt, retourneer een lege lijst
        }
        highScores.sort(Comparator.comparingInt(HighScoreEntry::getScore).reversed());
        return highScores;
    }

    public static void saveHighScores(List<HighScoreEntry> highScores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            for (HighScoreEntry entry : highScores) {
                writer.write(entry.getName() + ";" + entry.getScore());
                writer.newLine();
            }
            System.out.println("Highscores saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static boolean isTopScore(int score) {
        List<HighScoreEntry> highScores = getHighScores();
        return highScores.size() < MAX_HIGH_SCORES || score > highScores.get(highScores.size() - 1).getScore();
    }

    public static void addHighScore(String name, int score) {
        List<HighScoreEntry> highScores = getHighScores();
        highScores.add(new HighScoreEntry(name, score));
        highScores.sort(Comparator.comparingInt(HighScoreEntry::getScore).reversed());
        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores.remove(highScores.size() - 1);
        }
        saveHighScores(highScores);
    }

    public static class HighScoreEntry {
        private final String name;
        private final int score;

        public HighScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
