public class Inlist {
    public int first;
    public Inlist rest;

    public Inlist(int f, Inlist r){
        first = f;
        rest = r;
    }

    public int size(){
        if(rest == null){
            return 1;
        }
        return 1+this.rest.size();
    }

    public int iterativeSize(){
        int totalSize = 0;
        Inlist p = this;
        while(p != null){
            totalSize += 1;
            p = p.rest;
        }
//        p.rest = null;
        return totalSize;
    }

    public int get(int i){
        if(i == 0){
            return first;
        }
        return this.rest.get(i-1);
    }
}
