
package stokmobil;

public class stokMobil {
    private int id;
    private String nama_mobil;
    private String merk;
    private int stok;
    private String harga;

    public stokMobil(int id, String nama_mobil, String merk, int stok, String harga) {
        this.id = id;
        this.nama_mobil = nama_mobil;
        this.merk = merk;
        this.stok = stok;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public String getMerk() {
        return merk;
    }

    public int getStok() {
        return stok;
    }

    public String getHarga() {
        return harga;
    }
}
