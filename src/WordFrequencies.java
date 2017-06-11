    import java.io.*;
    import java.util.*;
    import java.util.stream.Collectors;

    import static java.lang.System.in;
    import static java.util.Collections.*;

    //http://www.javamadesoeasy.com/2015/04/sort-map-by-value-in-ascending-and.html
    class SortByValue implements Comparator<Map.Entry<String, Integer>>{

        @Override
        public int compare( Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2){
            return (entry2.getValue()).compareTo( entry1.getValue() );
        }
    }

    /**
     * Created by beep on 1/21/17.
     */
    public class WordFrequencies {
        public List<String> tokenize_backup(String filename) {
            ArrayList<String> res = new ArrayList<String>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename));
                String read = null;
                while ((read = br.readLine()) != null) {
                    String[] token = read.split("\\W+");
                    for (String part : token) {
                        part = part.toLowerCase();
                        res.add(part);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            return res;
        }

        public Map<String, Integer> tokenize(String filename) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename));
                String read = null;
                while ((read = br.readLine()) != null) {
                    String[] token = read.split("[\\W_]+");
                    for (String word : token) {
                        if(!word.isEmpty()) {
                            word = word.toLowerCase();
                            Integer count = map.get(word);
                            if (count != null) {
                                map.put(word, count + 1);
                            } else {
                                map.put(word, 1);
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            return map;
        }

        public Map<String, Integer> computeWordFrequencies(List<String> tokens) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(String word : tokens) {
                Integer count = map.get(word);
                if (count != null) {
                    map.put(word, count + 1);
                } else {
                    map.put(word, 1);
                }
            }
            return map;
        }

        public void print(Map<String, Integer> map) throws IOException {
            //values in the map are stored in descending order of values
            Map<String, Integer> tempMap = new TreeMap<String, Integer>(map);
            List<Map.Entry<String,Integer>> mymap = new ArrayList<Map.Entry<String,Integer>>(tempMap.entrySet());

            Collections.sort(mymap, new SortByValue());

            FileWriter writer = null;

            writer = new FileWriter("print_pride.txt");

            for(int i = 0; i < mymap.size(); i++) {
                StringBuilder result =  new StringBuilder();
                result.append(mymap.get(i).getKey())
                        .append(",")
                        .append(mymap.get(i).getValue())
                        .append("\n");
                writer.write(result.toString());
                System.out.print(result.toString());
            }
            writer.close();
        }

        public Set<String> tokenizeUnique(String filename) {
            Set<String> res = new TreeSet<String>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename));
                String read = null;
                while ((read = br.readLine()) != null) {
                    String[] token = read.split("[\\W_]+");
                    for (String part : token) {
                        if(!part.isEmpty()) {
                            part = part.toLowerCase();
                            if (!res.contains(part))
                                res.add(part);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            return res;
        }

        public Set<String> intersection(String filename1, String filename2) {
            Set<String> tokenize = tokenizeUnique(filename1);
            Set<String> set = new TreeSet<String>();
            int count = 0;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename2));
                String read = null;

                while ((read = br.readLine()) != null) {
                    String[] token = read.split("[\\W_]+");
                    for (String part : token) {
                        if(!part.isEmpty()) {
                        StringBuilder str = new StringBuilder(part.toLowerCase());
                        if(tokenize.contains(str.toString())) {
                            count++;
                            set.add(str.toString());
                            tokenize.remove(str.toString());
                        }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return set;
        }

        public void union(String filename1, String filename2) {
            String outputFile = "union.txt";
            try {
                BufferedReader fileRead1 = new BufferedReader(new FileReader(filename1));
                BufferedReader fileRead2 = new BufferedReader(new FileReader(filename2));
                BufferedWriter fileWrite = new BufferedWriter(new FileWriter(outputFile));
                boolean firstFileEOF = false;
                boolean secFileEOF = false;


                String read1 = null;
                String read2 = null;
                String[] freq1 = null;
                String[] freq2 = null;

                read1 = fileRead1.readLine();
                if(read1 == null) {
                    firstFileEOF = true;
                }
                if(firstFileEOF == false) {
                    freq1 = read1.split(",");

                    while (freq1.length != 2) {
                        read1 = fileRead1.readLine();
                        if (read1 == null) {
                            firstFileEOF = true;
                            break;
                        }
                        freq1 = read1.split(",");
                    }
                }
                read2 = fileRead2.readLine();
                if(read2 == null) {
                    secFileEOF = true;
                }
                if(secFileEOF == false) {
                    freq2 = read2.split(",");

                    while (freq2.length != 2) {
                        read2 = fileRead2.readLine();
                        if (read2 == null) {
                            secFileEOF = true;
                            break;
                        }
                        freq2 = read2.split(",");
                    }
                }
                while (firstFileEOF == false && secFileEOF == false) {
                    //if first file is greater than second file
                    while (freq1[0].compareToIgnoreCase(freq2[0]) > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(freq2[0]).append(",").append(freq2[1]);
                        fileWrite.write(sb.toString());
                        fileWrite.newLine();
                        read2 = fileRead2.readLine();
                        if(read2 == null) {
                            secFileEOF = true;
                            break;
                        }
                        freq2 = read2.split(",");
                        while (freq2.length != 2) {
                            read2 = fileRead2.readLine();
                            if(read2 == null) {
                                secFileEOF = true;
                                break;
                            }
                            freq2 = read2.split(",");
                        }
                        if(secFileEOF) break;
                    }


                    while (freq1[0].compareToIgnoreCase(freq2[0]) < 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(freq1[0]).append(",").append(freq1[1]);
                        fileWrite.write(sb.toString());
                        fileWrite.newLine();
                        read1 = fileRead1.readLine();
                        if(read1 == null) {
                            firstFileEOF = true;
                            break;
                        }
                        freq1 = read1.split(",");
                        while (freq1.length != 2) {
                            read1 = fileRead1.readLine();
                            if(read1 == null) {
                                firstFileEOF = true;
                                break;
                            }
                            freq1 = read1.split(",");
                        }
                        if(firstFileEOF) break;
                    }

                    while (0 == freq1[0].compareToIgnoreCase(freq2[0])) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(freq1[0])
                                .append(",")
                                .append(Integer.parseInt(freq1[1]) + Integer.parseInt(freq2[1]));
                        fileWrite.write(sb.toString());
                        fileWrite.newLine();
                        read1 = fileRead1.readLine();
                        if(read1 == null) {
                            firstFileEOF = true;
                           // break;
                        }
                        if (firstFileEOF == false) {
                            freq1 = read1.split(",");

                            while (freq1.length != 2) {
                                read1 = fileRead1.readLine();
                                if (read1 == null) {
                                    firstFileEOF = true;
                                    break;
                                }
                                freq1 = read1.split(",");
                            }
                        }


                        read2 = fileRead2.readLine();
                        if(read2 == null) {
                            secFileEOF = true;
                        }
                        if (secFileEOF == false) {
                            freq2 = read2.split(",");
                            while (freq2.length != 2) {
                                read2 = fileRead2.readLine();
                                if (read2 == null) {
                                    secFileEOF = true;
                                    break;
                                }
                                freq2 = read2.split(",");
                            }
                        }
                        if(secFileEOF || firstFileEOF) break;
                    }

                }
                if(firstFileEOF==false) {
                    freq1 = read1.split(",");
                    StringBuilder sb = new StringBuilder();
                    sb.append(freq1[0]).append(",").append(freq1[1]);
                    fileWrite.write(sb.toString());
                    fileWrite.newLine();

                    while ((read1 = fileRead1.readLine()) != null) {
                        freq1 = read1.split(",");
                        if(freq1.length!=2) continue;
                        sb = new StringBuilder();
                        sb.append(freq1[0]).append(",").append(freq1[1]);
                        fileWrite.write(sb.toString());
                        fileWrite.newLine();

                    }
                }
                if(secFileEOF==false) {
                    freq1 = read2.split(",");
                    StringBuilder sb = new StringBuilder();
                    sb.append(freq2[0]).append(",").append(freq2[1]);
                    fileWrite.write(sb.toString());
                    fileWrite.newLine();
                    while ((read2 = fileRead2.readLine()) != null) {
                        freq2 = read2.split(",");
                        if(freq2.length!=2) continue;
                        sb = new StringBuilder();
                        sb.append(freq2[0]).append(",").append(freq2[1]);
                        fileWrite.write(sb.toString());
                        fileWrite.newLine();

                    }
                }
                in.close();
                fileWrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }