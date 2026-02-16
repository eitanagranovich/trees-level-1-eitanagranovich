    public static int count(BinNode<Integer> node){
        if (node == null){
            return 0;
        } 
        int num = 0;
        int sum = 0;
        if (node.hasLeft()){
            sum = sum + node.getLeft().getValue();
        }
        if (node.hasRight()){
            sum = sum + node.getRight().getValue();
        }
        if (isPrime(sum)){
            num = 1;
        }
        return num + count(node.getLeft()) + count(node.getRight());
    }
    

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
