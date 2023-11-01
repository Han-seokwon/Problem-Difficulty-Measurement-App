package users;

public enum RANK {
    RANK5(0), RANK4(100), RANK3(200), RANK2(300), RANK1(400);
    
    private int requireRankPoint; // �ش� ��ũ�� �Ǳ� ���� �ʿ��� ��ũ����Ʈ
    
    private RANK(int rankPoint) {
        this.requireRankPoint = rankPoint;
    }
    
    public int getRequireRankPoint() {
        return this.requireRankPoint;
    }
    public RANK getNextRank() {
    	// ���� ���� Ÿ���� ��ü ����Ÿ�� ���̺��� ���� ��� ���� �������� ��ȯ
    	// �׷��� ������ ���� ���� Ÿ���� ������ Ÿ���̹Ƿ� ������ Ÿ�� ��ȯ
        return ordinal() < values().length - 1 ? values()[ordinal() + 1] : values()[-1];
    }
}
