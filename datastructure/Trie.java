

# Tire树 http://vickyqi.com/2015/11/23/数据结构系列——Trie树/

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

package com.taobao.basicc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author en.xuze@alipay.com
 * @version $Id: TrieUsedTest.java, v 0.1 2018年4月4日 上午10:37:42 en.xuze@alipay.com Exp $
 */
public class PrefixTrieTest {

    private PrefixTrie trie;

    @Before
    public void before() throws IOException {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        // 从文件中读取单词，构建TriedTree
        InputStreamReader read = new InputStreamReader(new FileInputStream(new File("/Users/en.xuze/Desktop/code/project/study/ws/basicc/src/main/java/words.txt")));
        BufferedReader reader = new BufferedReader(read);
        trie = new PrefixTrie();
        String line = null;
        while (null != (line = reader.readLine())) {
            line = line.trim();
            if (!pattern.matcher(line).matches()) {// 去除非法单词，如包含“-”
                continue;
            }
            trie.insertWord(line);
        }
    }

    /**
     * 测试使用TriedTree搜索前缀出现的次数
     */
    @Test
    public void searchPrefixWords() {
        String prefix = "vi";
        System.out.println(trie.selectPrefixCount(prefix));
        System.out.println(trie.selectWord("Vicky"));
    }
}

class PrefixTrie {

    private TrieNode root = new TrieNode('/');// TrieTree的根节点

    /**
     * 插入
     * 
     * @param word
     */
    public void insertWord(String word) {
        TrieNode index = this.root;
        for (char c : word.toLowerCase().toCharArray()) {
            index = index.addChild(c);
            index.addPrefixCount();
        }
        index.addCount();
        return;
    }

    /**
     * 查找
     * 
     * @param word
     * @return
     */
    public boolean selectWord(String word) {
        TrieNode index = this.root;
        for (char c : word.toLowerCase().toCharArray()) {
            index = index.getChild(c);
            if (null == index) {
                return false;
            }
        }
        return index.getCount() > 0;
    }

    /**
     * 查找前缀出现的次数
     * 
     * @param prefix
     * @return
     */
    public int selectPrefixCount(String prefix) {
        TrieNode index = this.root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            index = index.getChild(c);
            if (null == index) {
                return 0;
            }
        }
        return index.getPrefixCount();
    }

    /**
     * TrieTree的节点
     */
    private class TrieNode {

        /** 该节点的字符 */
        private final char nodeChar;//
        /** 一个TrieTree的节点的子节点 */
        private TrieNode[] childNodes = null;
        private int count = 0;// 单词数量，用于判断一个单词是否存在
        private int prefixCount = 0;// 前缀数量，用于查找该前缀出现的次数

        public TrieNode(char nodeChar) {
            super();
            this.nodeChar = nodeChar;
        }

        public TrieNode addChild(char ch) {
            int index = ch - 'a';
            if (null == childNodes) {
                this.childNodes = new TrieNode[26];
            }
            if (null == childNodes[index]) {
                childNodes[index] = new TrieNode(ch);
            }
            return childNodes[index];
        }

        public TrieNode getChild(char ch) {
            int index = ch - 'a';
            if (null == childNodes || null == childNodes[index]) {
                return null;
            }
            return childNodes[index];
        }

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        public void addPrefixCount() {
            this.prefixCount++;
        }

        public int getPrefixCount() {
            return this.prefixCount;
        }

        @Override
        public String toString() {
            return "TrieNode [nodeChar=" + nodeChar + "]";
        }
    }
}
