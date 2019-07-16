package com.DS.BinaryTree;

/**
 * Author:Sophie
 * Created: 2019/7/12
 */

/**
 * 递归会有一个栈来维护
 */
/**
 * 所有的二叉树都是围绕前序，中序，后序遍历的变种来的
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * 二叉树的遍历：
 * 1、前序遍历（NLR）
 * 2、中序遍历（LNR）
 * 3、后续遍历（ZRN）
 * 4、根据先序遍历字符串创建二叉树,后序遍历
 * 5、求树的结点个数：左子树的结点个数+右子树的结点个数+1
 * 6、求叶子节点的个数：左子树的叶子节点+右子树的叶子节点
 * 7、第K层的结点个数
 * 8、查找，依次在二叉树的根，左子树，右子树中查找value，如果找到，返回结点，否则返回null
 * 9、树的深度
 * 10、二叉树的前序遍历非递归（递归改成非递归，需要借助栈）
 * 11、二叉树的中序遍历（非递归）
 * 12、二叉树的后序遍历（非递归）
 * 13、二叉树的层序遍历
 * 14、判断是否是完全二叉树
 * 15、判断两棵树是否是相同的-->思路：相当于判断树的左子树是否相同 && 树的右子树是否相同
 *                          if（p==null && q==null） return true;//都为空树则相等
 *                          if((p==null && q!=null) || (p!=null && q==null)){return false}//一个为空，一个不为空则不相等
 *                          if(p.val!=q.val){return false} else{判断左子树和右子树是否相同}//如果结点的值不相同，则两棵树不相同
 * 16、另一棵树的子树-->给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * 17、判断二叉树是否是平衡二叉树
 *
 * 18、是否是对称二叉树
 * 19、根据二叉树创建字符串
 * 20、二叉树的最近公共祖先
 * 21、将二叉搜索树变成双向链表，二叉搜索树，中序遍历是一个有序的序列，
 *
 *
 */
public class TestBinaryTree {
    //创建一个二叉树
    static class TreeNode{
        char value;
        //引用类型不初始化，默认为null
        TreeNode left;
        TreeNode right;
        public TreeNode(char value){
            this.value=value;
        }
    }

    //二叉树前序遍历，递归
    public void PrevOrder(TreeNode root){
        if (root==null){
            return;
        }
        System.out.print(root.value+" ");
        PrevOrder(root.left);
        PrevOrder(root.right);
    }
    //二叉树前序遍历，非递归,要借助栈，

    /**
     * 如果root不为空，则将当前元素cur入栈，打印
     * 然后去看root的左结点，如果root的左节点不为空，则打印，再入栈，直到为空，说明左节点都打印完了，那么我们就要回退到父节点的右结点
     * 如何回退，取栈顶元素，top=stack.pop（），cur=top.right，若right为空，继续出栈，否则就遍历右子树
     * @param root
     */
    public void PreOrderNoR(TreeNode root){
        Stack<TreeNode>  stack=new Stack<>();
        TreeNode cur=root;
        TreeNode top=null;
        while (cur!=null || !stack.empty()){
            while (cur!=null){
                stack.push(cur);
                System.out.print(cur.value+" ");
                cur=cur.left;
            }
            top=stack.pop();
            cur= top.right;
        }
    }

    //二叉树中序遍历
    public void InOrder(TreeNode root){
        if (root==null){
            return;
        }
        InOrder(root.left);
        System.out.print(root.value+" ");
        InOrder(root.right);
    }
    //二叉树的中序遍历，非递归版本
    public void InOrderNoR(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        TreeNode top=null;
        while (cur!=null || !stack.empty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            top=stack.pop();
            System.out.print(top.value+" ");
            cur=top.right;
        }
    }
    //二叉树后续遍历
    public void postOrder(TreeNode root){
        if (root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value+" ");
    }
    //二叉树后续遍历，非递归版本
    //可能右子树已经被打印过了，需要记录，否则会死循环
    public void postOrderNoR(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        TreeNode prev=null;
        while (cur!=null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur=stack.peek();
            if (cur.right == null || cur.right==prev) {
                stack.pop();
                System.out.print(cur.value + " ");
                prev=cur;//记录的是是否已经被打印过
                cur=null;
            }else {
                cur=cur.right;
            }
        }
    }
    /**
     * 给定先序遍历，构造二叉树
     * ABC##DE##G##F
     * @param s
     * @return
     */
    public int i=0;
    TreeNode createBinaryTree(String s){
        TreeNode root=null;
        if (s.charAt(i)!='#'){
            root=new TreeNode(s.charAt(i));
            i++;
            root.left= createBinaryTree(s);
            root.right= createBinaryTree(s);
        }else {
            i++;
        }
        return root;
    }

    /**
     * 给定后序遍历，构造二叉树
     * ABC##DE##G##F
     * @param s
     * @return
     */
    //这个len是字符串的长度-1;
    public int len=6;
    TreeNode createBinaryTree1(String s){
        TreeNode root=null;
        if (s.charAt(len)!='#'){
            root=new TreeNode(s.charAt(len));
            len--;
            root.right= createBinaryTree1(s);
            root.left= createBinaryTree1(s);
        }else {
            len--;
        }
        return root;
    }

    /**
     * 二叉树的结点个数=左子树的结点个数+右子树的结点个数+1
     * 是后序遍历的变种
     * @param root
     * @return
     */
    public int getSize(TreeNode root){
        if (root==null){
            return 0;
        }
        return getSize(root.left)+ getSize(root.right)+1;
    }

    /**
     * 得到二叉树的叶子节点的个数
     * @param root
     */
    public int getLeaf(TreeNode root){
        if (root==null){
            return 0;
        }
        if (root.left==null && root.right==null){
            return 1;
        }
        return getLeaf(root.left)+getLeaf(root.right);
    }

    /**
     * 第K层的结点个数
     * @param k
     */
    public int getK(TreeNode root,int k){
        if (k==0){
            return 1;
        }

        return getK(root.left,k-1)+getK(root.right,k-1);

    }

    /**
     * 二叉树的查找
     * @param root
     * @param value
     */
    TreeNode find(TreeNode root,int value){
        if (root==null){
            return null;
        }
        if (root.value==value){
            return root;
        }
        TreeNode ret=find(root.left,value);
        if (ret!=null){
            return ret;
        }
        ret=find(root.right,value);
        if (ret!=null){
            return ret;
        }
        return null;
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            //递归要注意减少不必要压栈,可能会超出时间限制
//        return max(height(root.left),height(root.right))+1;
            int leftheight = height(root.left);
            int rightheight = height(root.right);
            return leftheight > rightheight ? leftheight + 1 : rightheight + 1;
        }
    }
    //二叉树的层序遍历，要借助队列
    //queue的add，offer的区别
    public void LevelOrder(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode cur=null;
        if (root==null){
            return;
        }else {
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            cur=queue.poll();
            System.out.print(cur.value+" ");
            if (cur.left!=null){
                queue.offer(cur.left);
            }
            if (cur.right!=null){
                queue.offer(cur.right);
            }
        }
    }
    //判断一棵树是否是完全二叉树，返回0代表是完全二叉树
    //思路：借助队列，空null也要放到队列中，队列不为空出栈，判断出栈元素是否为空，为空，结束循环，判断队列中是否还有结点，若没有，则是完全二叉树，否则不是
    public int isComplete(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        if (root!=null){
            queue.offer(root);
        }
        while (!queue.isEmpty()){
            TreeNode top=queue.poll();
            if (top!=null){
                queue.offer(top.left);
                queue.offer(top.right);
            }else {
                break;
            }
        }
        //遍历队列，如果剩下的有一个不为空，则不是完全二叉树
        while (!queue.isEmpty()){
           TreeNode top=queue.peek();
           if (top==null){
               queue.poll();
           }else {
               //不是完全二叉树
               return -1;
           }
        }
        return 0;
    }

    //判断是否是相同的树
    public boolean isSameTree(TreeNode s,TreeNode t){
        if ((s==null && t!=null) || (s!=null && t==null)){
            return false;
        }
        if (s==null && t==null){
            return true;
        }
        if (s.value!=t.value){
            return false;
        }
        return isSameTree(s.left,t.left) && isSameTree(s.right,t.right);
    }

    //判断一个树是否是另一个树的子树:判断两个子树是否相同，
    // 不相同则到左子树里面找，没有的话，到右子树里面找，相当于是先序遍历
    //最后找不到，返回false
    public boolean isSubtree(TreeNode s,TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        } else if (isSubtree(s.left, t)) {
            return true;
        } else if (isSubtree(s.right, t)) {
            return true;
        } else {
            return false;
        }
    }


    //是否是平衡二叉树
    public boolean isBalanced(TreeNode root){
        if (root==null){
            return true;
        }
        //得到左右两子树的高度
        int leftheight=height(root.left);
        int rightheight=height(root.right);
        if (abs(leftheight-rightheight)>1){
            return  false;
        }else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
//        return abs(leftheight-rightheight)<2 && isBalanced(root.left) && isBalanced(root.right);
    }

    //是否是对称二叉树，也就是镜像二叉树

    /**
     * 思路：是否是对称二叉树，root为空，是镜像二叉树；不为空，进行下面的判断；
     * 要判断，左子树和右子树之间有一个为空，另一个不为空，不是镜像二叉树；两个都为空，是镜像二叉树，
     * 两子树的root结点值不同，不是镜像二叉树，否则，进行下卖弄的判断
     * 左子树的左子树个右子树的右子树是否相同，
     *                          左子树的右子树是否和右子树的左子树相同
     *                          若相同，则是镜像二叉树
     *
     * @param root
     * @return
     */
    public  boolean isSymmetric(TreeNode root){
        if (root==null){
            return true;
        }
        return isSymmetricChild(root.left,root.right);
    }

    private boolean isSymmetricChild(TreeNode left, TreeNode right) {
        if (left==null && right==null){
            return true;
        }
        if ((left==null && right!=null) || (left!=null && right==null)){
            return false;
        }
        if (left.value!=right.value){
            return false;
        }
        return isSymmetricChild(left.left,right.right)
                && isSymmetricChild(left.right,right.left);
    }

    //根据二叉树创建字符串
    public String tree2str(TreeNode t){
        StringBuilder sb=new StringBuilder();
       tree2strChild(t,sb);
       return sb.toString();
    }


    private void tree2strChild(TreeNode t, StringBuilder sb) {
        if (t==null){
            return ;
        }
        sb.append(t.value);
        if (t.left!=null){
            sb.append("(");
            tree2strChild(t.left,sb);
            sb.append(")");
        }else {
            if (t.right == null) {
                return ;
            }else {
                sb.append("()");
            }
        }
        if (t.right!=null){
            sb.append("(");
            tree2strChild(t.right,sb);
            sb.append(")");
        }else {
            return;
        }
    }

    //二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        //p,q中有一个是root,最先找到的结点为最近公共祖先
        if (root==p || root==q){
            return root;
        }
        //去左边找，去右边找
        TreeNode leftTree=lowestCommonAncestor(root.left,p,q);
        TreeNode rightTree=lowestCommonAncestor(root.right,p,q);

        if (leftTree!=null && rightTree!=null){
            return root;
        }else if (leftTree!=null){
            return leftTree;
        }else if (rightTree!=null){
            return rightTree;
        }
        return null;
    }

    //二叉搜索树变成双向链表
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConvertChild(pRootOfTree);
        TreeNode head=pRootOfTree;
        while (head!=null && head.left!=null){
            head=head.left;
        }
        return head;
    }
    TreeNode pre=null;
    public void ConvertChild(TreeNode pCur){
        if (pCur==null){
            return ;
        }
        ConvertChild(pCur.left);
        pCur.left=pre;
        if (pre!=null){
            pre.right=pCur;
        }
        pre=pCur;
        ConvertChild(pCur.right);
    }


    //根据一棵树的前序遍历与中序遍历构造二叉树
    /**
     * 步骤：1、从前序遍历数组的第一位开始，new 一个根节点，
     * 2、然后在中序遍历中去找这个值对应的下标，
     * 3、将中序遍历就分成了两个部分,对两部分分别再去找根节点，保存为leftroot，rightroot
     * 4、root.Left=leftroot,root.right=rightroot;
     */
    public int preindex=0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //只根据一个序列是得不到树的
        if (preorder.length==0 || inorder.length==0){
            return null;
        }
        int inbegin=0;
        int inend=preorder.length;
        TreeNode root= build(preorder,inorder,inbegin,inend);
        return root;
    }
    private TreeNode build(int[] preorder, int[] inorder, int inbegin, int inend) {

        if (inbegin>inend){
            return null;
        }
        TreeNode root=new TreeNode((char) preorder[preindex]);
        int rindex=findRI(inorder,preorder[preindex]);
        preindex++;
        TreeNode leftroot=build(preorder,inorder,inbegin,rindex-1);
        TreeNode rightroot=build(preorder,inorder,rindex+1,inend);
        root.left=leftroot;
        root.right=rightroot;
        return root;
    }
    private int findRI(int[] inorder, int rindex) {
        for (int j=0;j<inorder.length;j++){
            if (rindex==inorder[j]){
                return j;
            }
        }
        return -1;
    }

    //根据后序遍历和中序遍历去创建二叉树,同理，不过倒着走的时候，要先创建右结点


    public static void main(String[] args) {
        TestBinaryTree tree=new TestBinaryTree();

        TreeNode root1=new TreeNode('A');
        TreeNode left=new TreeNode('B');
        TreeNode right=new TreeNode('C');
        root1.left=left;
        root1.right=right;
        System.out.println(tree.tree2str(root1));
        //通过前序遍历的字符串，构建二叉树
//        TreeNode root=tree.createBinaryTree("ABC##DE#G##F###");
        //通过后序遍历的字符串，创建二叉树
        TreeNode root=tree.createBinaryTree1("##B##CA");
        System.out.println("二叉树的前序遍历递归版本：");
        tree.PrevOrder(root);
        System.out.println();
        System.out.println("二叉树的前序遍历非递归版本");
        tree.PreOrderNoR(root);
        System.out.println();
        System.out.println("二叉树的中序遍历递归版本：");
        tree.InOrder(root);
        System.out.println();
        System.out.println("二叉树的中序遍历非递归版本：");
        tree.InOrderNoR(root);
        System.out.println();
        System.out.println("二叉树的后序遍历递归版本：");
        tree.postOrder(root);
        System.out.println();
        System.out.println("二叉树的后序遍历非递归版本：");
        tree.postOrderNoR(root);
        System.out.println();
        System.out.println("二叉树的层序遍历：");
        tree.LevelOrder(root);
        System.out.println();
        System.out.print("这颗树是否是完全二叉树：");
        if (tree.isComplete(root)==0){
            System.out.print("是");
        }else {
            System.out.print("不是");
        }
        System.out.println();
        System.out.println("树的结点个数："+tree.getSize(root));
        System.out.println("树的叶子节点个数："+tree.getLeaf(root));
        System.out.println("树的第0层结点个数："+tree.getK(root,0));
        System.out.println("树的第1层结点个数："+tree.getK(root,1));
        System.out.println("找'C'："+tree.find(root,'C').value);
    }
}
