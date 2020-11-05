package net.artemy;

import java.util.HashMap;
import java.util.Map;

public class Period {
    private final static String MATH_NAME = "Алгебра";
    private final static String BIOLOGY_NAME = "Биология";
    private final static String GEOGRAPHY_NAME = "География";
    private final static String GEOMETRY_NAME = "Геометрия";
    private final static String PAINTING_NAME = "Изобразительное искусство";
    private final static String FOREIGN_LANGUAGE_NAME = "Иностранный язык (английский)";
    private final static String INFORMATICS_NAME = "Информатика";
    private final static String HISTORY_NAME = "История";
    private final static String LITERATURE_NAME = "Литература";
    private final static String MISIC_NAME = "Музыка";
    private final static String SOCIAL_SCIENCE_NAME = "Обществознание";
    private final static String NATIVE_LANGUAGE_NAME = "Родной язык (русский)";
    private final static String RUSSIAN_NAME = "Русский язык";
    private final static String TECHNOLOGY_NAME = "Технология";
    private final static String PHYSICS_NAME = "Физика";
    private final static String SPORT_NAME = "Физическая культура";
    private String periodName;
    private Integer math;
    private Integer biology;
    private Integer geography;
    private Integer geometry;
    private Integer painting;
    private Integer foreignLanguage;
    private Integer informatics;
    private Integer history;
    private Integer literature;
    private Integer music;
    private Integer socialStudies;
    private Integer russian;
    private Integer nativeLanguage;
    private Integer technology;
    private Integer physics;
    private Integer sport;
    private Map<String, Integer> marks;

    public Period() {
        marks = new HashMap<String, Integer>();
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void addMark(String subject, Integer mark) {
        marks.put(subject, mark);
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        if (math != null) {
            marks.put(MATH_NAME, math);
        }
        this.math = math;
    }

    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        if (biology != null) {
            marks.put(BIOLOGY_NAME, biology);
        }
        this.biology = biology;
    }

    public Integer getGeography() {
        return geography;
    }

    public void setGeography(Integer geography) {
        if (geography != null) {
            marks.put(GEOGRAPHY_NAME, geography);
        }
        this.geography = geography;
    }

    public Integer getGeometry() {
        return geometry;
    }

    public void setGeometry(Integer geometry) {
        if (geometry != null) {
            marks.put(GEOMETRY_NAME, geometry);
        }
        this.geometry = geometry;
    }

    public Integer getPainting() {
        return painting;
    }

    public void setPainting(Integer painting) {
        if (painting != null) {
            marks.put(PAINTING_NAME, painting);
        }
        this.painting = painting;
    }

    public Integer getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(Integer foreignLanguage) {
        if (foreignLanguage != null) {
            marks.put(FOREIGN_LANGUAGE_NAME, foreignLanguage);
        }
        this.foreignLanguage = foreignLanguage;
    }

    public Integer getInformatics() {
        return informatics;
    }

    public void setInformatics(Integer informatics) {
        if (informatics != null) {
            marks.put(INFORMATICS_NAME, informatics);
        }
        this.informatics = informatics;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        if (history != null) {
            marks.put(HISTORY_NAME, history);
        }
        this.history = history;
    }

    public Integer getLiterature() {
        return literature;
    }

    public void setLiterature(Integer literature) {
        if (literature != null) {
            marks.put(LITERATURE_NAME, literature);
        }
        this.literature = literature;
    }

    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        if (music != null) {
            marks.put(MISIC_NAME, music);
        }
        this.music = music;
    }

    public Integer getSocialStudies() {
        return socialStudies;
    }

    public void setSocialStudies(Integer socialStudies) {
        if (socialStudies != null) {
            marks.put(SOCIAL_SCIENCE_NAME, socialStudies);
        }
        this.socialStudies = socialStudies;
    }

    public Integer getRussian() {
        return russian;
    }

    public void setRussian(Integer russian) {
        if (russian != null) {
            marks.put(RUSSIAN_NAME, russian);
        }
        this.russian = russian;
    }

    public Integer getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(Integer nativeLanguage) {
        if (nativeLanguage != null) {
            marks.put(NATIVE_LANGUAGE_NAME, nativeLanguage);
        }
        this.nativeLanguage = nativeLanguage;
    }

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        if (technology != null) {
            marks.put(TECHNOLOGY_NAME, technology);
        }
        this.technology = technology;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        if (physics != null)
            marks.put(PHYSICS_NAME, physics);
        this.physics = physics;
    }

    public Integer getSport() {
        return sport;
    }

    public void setSport(Integer sport) {
        if (sport != null)
            marks.put(SPORT_NAME, sport);
        this.sport = sport;
    }
}
