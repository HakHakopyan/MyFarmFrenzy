public abstract class Plant extends Generator {
    final  int READY_TYME;
    final Season season;

    public Season getSeason() {
        return this.season;
    }

    protected Plant(int readyTime, Season season) {
        super(readyTime);
        this.READY_TYME = readyTime;
        this.season = season;
    }

    public void addTime() {
        if (this.timer < this.READY_TYME) {
            this.timer += 1;
        }
    }

    @Override
    public Crop getDelivery() {
        return null;
    }

    /**
     * return the crop that our plant gave
     * @return new Crop.Crop
     */
    @Override
    public Boolean deliveryIsPossible() {
        if (this.timer >= this.READY_TYME)
            return true;
        return  false;
    }

}
