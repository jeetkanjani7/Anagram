/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import android.util.Log;
public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static final String Tag="MyActivity";
    private ArrayList<String> wordlist=new ArrayList<String>();
    private HashSet<String> wordset=new HashSet<String>();
    private HashMap<String,ArrayList<String>> letterstowords=new HashMap<String,ArrayList<String>>();
     public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);


        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordlist.add(word);
            wordset.add(word);
            Log.v(Tag, "hey theresss");
        }
    }


    public boolean isGoodWord(String word, String base) {
        if(wordset.contains(word))
        {
            if(!word.contains(base))
            {
                return true;
            }
        }
        return false;
    }

    public List<String> getAnagrams(String targetWord) {

        ArrayList<String> result = new ArrayList<String>();

        String sorted=SortLetters(targetWord.trim());
        for(String s: wordlist)
        {
            if(s.length()==sorted.length())
            {
                String teststring=SortLetters(s);
                if(teststring.equals(sorted))
                {
                    if(letterstowords.containsKey(teststring))
                    {
                        if(!result.contains(s))
                        {
                            result.add(s);
                            letterstowords.put(teststring,result);
                        }
                    }
                    else
                    {
                        result.add(teststring);
                        letterstowords.put(teststring,result);
                    }
                    Log.v("ResultMessage","Anagram!!!"+s);
                }
            }
        }
        return result;
    }
    public String SortLetters(String input)
    {
        int length=input.length();
        char sorted[]=new char[length];
        for(int i=0;i<=length-1;i++)
        {
            sorted[i]=input.charAt(i);
        }

        for(int j=0;j<length-1;j++)
        {
            for(int k=0;k<length-1-j;k++)
            {
                if(sorted[k]>sorted[k+1])
                {
                    char temp=sorted[k];
                    sorted[k]=sorted[k+1];
                    sorted[k+1]=temp;
                }
            }
        }
        String output=new String(sorted);
        Log.v("SortedString",output);

        return output;
    }
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
