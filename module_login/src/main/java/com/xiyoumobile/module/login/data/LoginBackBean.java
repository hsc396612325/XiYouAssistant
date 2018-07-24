package com.xiyoumobile.module.login.data;

public class LoginBackBean {
    private boolean IsSucceed;
    private String Msg;
    private Obj Obj;
    public void setIsSucceed(boolean IsSucceed) {
        this.IsSucceed = IsSucceed;
    }
    public boolean getIsSucceed() {
        return IsSucceed;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
    public String getMsg() {
        return Msg;
    }

    public void setObj(Obj Obj) {
        this.Obj = Obj;
    }
    public Obj getObj() {
        return Obj;
    }

    public class Obj {

        private String NO;
        private String SNO;
        private String NAME;
        private int SEX;
        private String XY;
        private String XYName;
        private String ZY;
        private String ZYName;
        private String NJ;
        private String NjCode;
        private String BJ;
        private String SFLX;
        private String SFLXMC;
        private String IDNO;
        private int IDTYPE;
        private String CARDID;
        private int ACCOUNT;
        private String TYPE;
        private String Academy;
        public void setNO(String NO) {
            this.NO = NO;
        }
        public String getNO() {
            return NO;
        }

        public void setSNO(String SNO) {
            this.SNO = SNO;
        }
        public String getSNO() {
            return SNO;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }
        public String getNAME() {
            return NAME;
        }

        public void setSEX(int SEX) {
            this.SEX = SEX;
        }
        public int getSEX() {
            return SEX;
        }

        public void setXY(String XY) {
            this.XY = XY;
        }
        public String getXY() {
            return XY;
        }

        public void setXYName(String XYName) {
            this.XYName = XYName;
        }
        public String getXYName() {
            return XYName;
        }

        public void setZY(String ZY) {
            this.ZY = ZY;
        }
        public String getZY() {
            return ZY;
        }

        public void setZYName(String ZYName) {
            this.ZYName = ZYName;
        }
        public String getZYName() {
            return ZYName;
        }

        public void setNJ(String NJ) {
            this.NJ = NJ;
        }
        public String getNJ() {
            return NJ;
        }

        public void setNjCode(String NjCode) {
            this.NjCode = NjCode;
        }
        public String getNjCode() {
            return NjCode;
        }

        public void setBJ(String BJ) {
            this.BJ = BJ;
        }
        public String getBJ() {
            return BJ;
        }

        public void setSFLX(String SFLX) {
            this.SFLX = SFLX;
        }
        public String getSFLX() {
            return SFLX;
        }

        public void setSFLXMC(String SFLXMC) {
            this.SFLXMC = SFLXMC;
        }
        public String getSFLXMC() {
            return SFLXMC;
        }

        public void setIDNO(String IDNO) {
            this.IDNO = IDNO;
        }
        public String getIDNO() {
            return IDNO;
        }

        public void setIDTYPE(int IDTYPE) {
            this.IDTYPE = IDTYPE;
        }
        public int getIDTYPE() {
            return IDTYPE;
        }

        public void setCARDID(String CARDID) {
            this.CARDID = CARDID;
        }
        public String getCARDID() {
            return CARDID;
        }

        public void setACCOUNT(int ACCOUNT) {
            this.ACCOUNT = ACCOUNT;
        }
        public int getACCOUNT() {
            return ACCOUNT;
        }

        public void setTYPE(String TYPE) {
            this.TYPE = TYPE;
        }
        public String getTYPE() {
            return TYPE;
        }

        public void setAcademy(String Academy) {
            this.Academy = Academy;
        }
        public String getAcademy() {
            return Academy;
        }

    }
}

