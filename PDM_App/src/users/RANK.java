package users;

public enum RANK {
    RANK5(0), RANK4(100), RANK3(200), RANK2(300), RANK1(400);
    
    private int requireRankPoint; // 해당 랭크가 되기 위해 필요한 랭크포인트
    
    private RANK(int rankPoint) {
        this.requireRankPoint = rankPoint;
    }
    
    public int getRequireRankPoint() {
        return this.requireRankPoint;
    }
    public RANK getNextRank() {
    	// 현재 열거 타입이 전체 열거타입 길이보다 작은 경우 다음 열거형을 반환
    	// 그렇지 않으면 현재 열거 타입이 마지막 타입이므로 마지막 타입 반환
        return ordinal() < values().length - 1 ? values()[ordinal() + 1] : values()[-1];
    }
}
