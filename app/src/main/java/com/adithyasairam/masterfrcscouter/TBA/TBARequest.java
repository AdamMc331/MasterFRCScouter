package com.adithyasairam.masterfrcscouter.TBA;

import android.os.Environment;

import com.adithyasairam.TheBlueAlliance.DataRequest;
import com.adithyasairam.TheBlueAllianceModels.Event;
import com.adithyasairam.TheBlueAllianceModels.EventAwards;
import com.adithyasairam.TheBlueAllianceModels.EventMatches;
import com.adithyasairam.TheBlueAllianceModels.EventRankings;
import com.adithyasairam.TheBlueAllianceModels.EventTeams;
import com.adithyasairam.TheBlueAllianceModels.MatchInformation_2015;
import com.adithyasairam.TheBlueAllianceModels.TeamEventAwards;
import com.adithyasairam.TheBlueAllianceModels.TeamEventMatches;
import com.adithyasairam.TheBlueAllianceModels.TeamEvents;
import com.adithyasairam.TheBlueAllianceModels.TeamHistoryAwards;
import com.adithyasairam.TheBlueAllianceModels.TeamHistoryEvents;
import com.adithyasairam.TheBlueAllianceModels.TeamInformation;
import com.adithyasairam.TheBlueAllianceModels.TeamMedia;
import com.adithyasairam.Utils.Annotations.Changeable;
import com.adithyasairam.masterfrcscouter.Backend.DataRW;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Adi on 7/26/2015.
 * @author Adi
 */
public class TBARequest {
    private static File basePath = getBasePath();

    private static File getBasePath() {
        File appDir = new File(Environment.getExternalStorageDirectory() + "/MasterFRCScouter");
        appDir.mkdirs();
        File dir = new File(appDir.getAbsolutePath() + "/CachedData");
        dir.mkdirs();
        return dir;
    }

    private static File makeNewFile(String fileName) {
        return new File(basePath.getAbsolutePath(), fileName);
    }

    public static Set<String> getAllKeys() {
        return DataRW.filePathsMap.keySet();
    }

    public static Collection<File> getAllValues() {
        return DataRW.filePathsMap.values();
    }

    public static TeamEventAwards.Award[] GetTeamEventAwards(String teamKey, String eventKey, boolean forceCacheData) {
        ArrayList<TeamEventAwards.Award> teamEventAwardsToReturn = new ArrayList<TeamEventAwards.Award>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "awardsat" + eventKey;
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/event/" + eventKey + "/awards");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamEventAwardsToReturn = gson.fromJson(data, new ArrayList<TeamEventAwards.Award>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamEventAwardsToReturn.toArray(), teamEventAwardsToReturn.toArray().length, TeamEventAwards.Award[].class);
    }

    public static TeamEventMatches.Match[] GetTeamEventMatches(String teamKey, String eventKey, boolean forceCacheData) {
        ArrayList<TeamEventMatches.Match> teamEventMatchesToReturn = new ArrayList<TeamEventMatches.Match>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "matchesat" + eventKey;
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/event/" + eventKey + "/matches");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamEventMatchesToReturn = gson.fromJson(data, new ArrayList<TeamEventMatches.Match>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamEventMatchesToReturn.toArray(), teamEventMatchesToReturn.toArray().length, TeamEventMatches.Match[].class);
    }

    public static TeamEvents.Event[] GetTeamEvents(String teamKey, int year, boolean forceCacheData) {
        ArrayList<TeamEvents.Event> teamEventsToReturn = new ArrayList<TeamEvents.Event>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "eventsduring" + year;
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/" + year + "/events");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamEventsToReturn = gson.fromJson(data, new ArrayList<TeamEvents.Event>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamEventsToReturn.toArray(), teamEventsToReturn.toArray().length, TeamEvents.Event[].class);
    }

    public static TeamHistoryAwards.Award[] GetTeamHistoricalAwards(String teamKey, boolean forceCacheData) {
        ArrayList<TeamHistoryAwards.Award> teamHistoricalAwardsToReturn = new ArrayList<TeamHistoryAwards.Award>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "historicawards";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/history/awards");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamHistoricalAwardsToReturn = gson.fromJson(data, new ArrayList<TeamHistoryAwards.Award>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamHistoricalAwardsToReturn.toArray(), teamHistoricalAwardsToReturn.toArray().length, TeamHistoryAwards.Award[].class);
    }

    public static TeamHistoryEvents.Event[] GetTeamHistoricalEvents(String teamKey, boolean forceCacheData) {
        ArrayList<TeamHistoryEvents.Event> teamHistoricalEventsToReturn = new ArrayList<TeamHistoryEvents.Event>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "historicevents";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/history/events");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamHistoricalEventsToReturn = gson.fromJson(data, new ArrayList<TeamHistoryEvents.Event>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamHistoricalEventsToReturn.toArray(), teamHistoricalEventsToReturn.toArray().length, TeamHistoryEvents.Event[].class);
    }

    public static TeamInformation GetTeamInformation(String teamKey, boolean forceCacheData) {
        TeamInformation teamInformationToReturn = new TeamInformation();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "teaminformation";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey);
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamInformationToReturn = gson.fromJson(data, TeamInformation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamInformationToReturn;
    }

    public static TeamMedia.MediaLocation[] GetTeamMediaLocations(String teamKey, int year, boolean forceCacheData) {
        ArrayList<TeamMedia.MediaLocation> teamMediaLocationsToReturn = new ArrayList<TeamMedia.MediaLocation>();
        Gson gson = new GsonBuilder().create();
        String key = teamKey + "medialocationsduring" + year;
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/team/" + teamKey + "/" + year + "/media");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamMediaLocationsToReturn = gson.fromJson(data, new ArrayList<TeamMedia.MediaLocation>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamMediaLocationsToReturn.toArray(), teamMediaLocationsToReturn.toArray().length, TeamMedia.MediaLocation[].class);
    }

    public static Event.EventInformation GetEventInformation(String eventKey, boolean forceCacheData) {
        Event.EventInformation eventToReturn = new Event.EventInformation();
        Gson gson = new GsonBuilder().create();
        String key = eventKey + "eventinformation";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/event/" + eventKey);
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), data, key);
            }
            eventToReturn = gson.fromJson(data, Event.EventInformation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventToReturn;
    }

    public static EventAwards.Award[] GetEventAwards(String eventKey, boolean forceCacheData) {
        ArrayList<EventAwards.Award> dataList = new ArrayList<EventAwards.Award>();
        Gson gson = new GsonBuilder().create();
        String key = eventKey + "awards";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/event/" + eventKey + "/awards");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), data, key);
            }
            dataList = gson.fromJson(data, new ArrayList<EventAwards.Award>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, EventAwards.Award[].class);
    }

    public static EventMatches.Match[] GetEventMatches(String eventKey, boolean forceCacheData) {
        ArrayList<EventMatches.Match> dataList = new ArrayList<EventMatches.Match>();
        Gson gson = new GsonBuilder().create();
        String key = eventKey + "matches";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/event/" + eventKey + "/matches");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            dataList = gson.fromJson(data, new ArrayList<EventMatches.Match>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, EventMatches.Match[].class);
    }

    public static EventRankings.Team[] GetEventRankings(String eventKey, boolean forceCacheData) {
        ArrayList<EventRankings.Team> teamList = new ArrayList<EventRankings.Team>();
        Gson gson = new GsonBuilder().create();
        String key = eventKey + "eventrankings";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/event/" + eventKey + "/rankings");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            ArrayList<Object> dataList = gson.fromJson(data, new ArrayList<Object>() {
            }.getClass());
            for (int i = 1; i < dataList.size(); i++) {
                EventRankings.Team teamToAdd = new EventRankings.Team();
                {
                    Object[][] array = (Object[][]) dataList.toArray();
                    teamToAdd.Rank = (Integer) array[i][0]; //klm = 20
                    teamToAdd.Team_Number = (Integer) array[i][1];
                    teamToAdd.Qual_Average = (Double) array[i][2];
                    teamToAdd.Auto = (Integer) array[i][3];
                    teamToAdd.Container = (Integer) array[i][4];
                    teamToAdd.Coopertition = (Integer) array[i][5];
                    teamToAdd.Litter = (Integer) (array[i][6]);
                    teamToAdd.Tote = (Integer) (array[i][7]);
                    teamToAdd.Played = (Integer) array[i][8];
                }
                teamList.add(teamToAdd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamList.toArray(), teamList.toArray().length, EventRankings.Team[].class);
    }

    public static EventTeams.Team[] GetEventTeamsList(String eventKey, boolean forceCacheData) {
        ArrayList<EventTeams.Team> teamList = new ArrayList<EventTeams.Team>();
        Gson gson = new GsonBuilder().create();
        String key = eventKey + "teams";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/event/" + eventKey + "/teams");
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            teamList = gson.fromJson(data, new ArrayList<EventTeams.Team>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(teamList.toArray(), teamList.toArray().length, EventTeams.Team[].class);
    }

    public static com.adithyasairam.TheBlueAllianceModels.Events.Event[] GetEvents(int year, boolean forceCacheData) {
        ArrayList<com.adithyasairam.TheBlueAllianceModels.Events.Event> dataList = new ArrayList<com.adithyasairam.TheBlueAllianceModels.Events.Event>();
        Gson gson = new GsonBuilder().create();
        String key = year + "events";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = ("http://www.thebluealliance.com/api/v2/events/" + year);
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
            }
            dataList = gson.fromJson(data, new ArrayList<com.adithyasairam.TheBlueAllianceModels.Events.Event>() {
            }.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(dataList.toArray(), dataList.toArray().length, com.adithyasairam.TheBlueAllianceModels.Events.Event[].class);
    }

    @Changeable(source = com.adithyasairam.masterfrcscouter.TBA.TBARequest.class,
            when = Changeable.When.YEARLY, priority = Changeable.Priority.HIGH, type = Changeable.Type.METHOD)
    public static MatchInformation_2015.Match GetMatchInformation2015(String matchKey, boolean forceCacheData) {
        MatchInformation_2015.Match match = new MatchInformation_2015.Match();
        Gson gson = new GsonBuilder().create();
        String key = matchKey + "matchinfo";
        String data = "";
        try {
            if (forceCacheData) {
                if (DataRW.mapContains(key)) {
                    DataRW.removeMapEntry(key);
                }
            } else if (DataRW.mapContains(key)) {
                data = DataRW.getStringFromFile(key);
            } else {
                String url = "http://www.thebluealliance.com/api/v2/match/" + matchKey;
                data = DataRequest.getDataFromTBA(url);
                DataRW.writeStringAsFile(makeNewFile(key + ".html"), key, data);
                match = gson.fromJson(data, new MatchInformation_2015.Match() {
                }.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }
}