class MyTestingClass {
    private int id;
    private String data;

    public MyTestingClass(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;

        if (data != null) {
            for (int i = 0; i < data.length(); i++) {
                hash = 31 * hash + data.charAt(i);
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && (data != null ? data.equals(that.data) : that.data == null);
    }
}

