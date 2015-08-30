package zhiyou.requestpaging;

/**
 * Created by zhiyou on 15-8-4.
 */
public class Pagebean {
    private int page;//第几页
    private int rows;//一页多少行
    private  int start;//开始的页数

    public Pagebean(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return (page-1)*rows;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
