package wz.wzksb.cert.bos;

 class MyBostype implements IBostype {
    private final String bt;

    public MyBostype(String bt) {
        this.bt=bt;
    }
    public String  toString(){
        return this.bt;
    }
}
