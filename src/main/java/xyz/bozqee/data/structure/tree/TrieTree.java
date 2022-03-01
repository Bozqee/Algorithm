package xyz.bozqee.data.structure.tree;

/**
 * 字典树
 */
public class TrieTree {

    public boolean insert(TrieNode root, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] ch = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            //如果该分支不存在，需要创建
            if (root.nextNode[ch[i] - 'a'] == null) {
                root.nextNode[ch[i] - 'a'] = new TrieNode();
            }
            root = root.nextNode[ch[i] - 'a'];
            root.prefix++;
        }
        root.count++;
        return true;
    }

    /**
     * 查询是否存在
     */
    public boolean search(TrieNode root, String word) {
        return searchWordCount(root, word) != -1;
    }

    /**
     * 查询是否存在
     */
    public int searchWordCount(TrieNode root, String word) {
        if (root == null || word.length() == 0) {
            return -1;
        }
        char[] ch = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            //如果该分支不存在，表名该单词不存在
            if (root.nextNode[ch[i] - 'a'] == null) {
                return -1;
            }
            //如果存在，则继续向下遍历
            root = root.nextNode[ch[i] - 'a'];
        }
        //如果count==0,也说明该单词不存在
        if (root.count == 0) {
            return -1;
        }
        return root.count;
    }


    //查询以str为前缀的单词数量
    public int searchPrefix(TrieNode root, String str) {
        if (root == null || str.length() == 0) {
            return -1;
        }
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            //如果该分支不存在，表名该单词不存在
            if (root.nextNode[ch[i] - 'a'] == null) {
                return -1;
            }
            //如果存在，则继续向下遍历
            root = root.nextNode[ch[i] - 'a'];
        }
        return root.prefix;
    }


    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();

        TrieNode root = new TrieNode();
        trieTree.insert(root, "hello");
        System.out.println(trieTree.search(root, "helloworld"));
    }

    /**
     * 其中count表示以当前单词结尾的单词数量。
     * prefix表示以该处节点之前的字符串为前缀的单词数量。
     */
    static class TrieNode {
        int count;
        int prefix;
        TrieNode[] nextNode = new TrieNode[26];

        public TrieNode() {
            count = 0;
            prefix = 0;
        }
    }


}
