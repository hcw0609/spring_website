package com.test.util;

public class Pagination {

    // 페이징 관련
    private int per_page_post = 10;  // 페이지당 보여줄 게시글 수
	private int per_block_page = 5;  // 블록당 보여줄 페이지 수
    private int cur_page = 1;        // 현재 페이지
    private int cur_block = 1;       // 현재 블록
    private int post_cnt;            // 총 게시글 수
    private int page_cnt;            // 총 페이지 수
    private int block_cnt;           // 총 블록 수
    private int start_page;          // 시작 페이지
    private int end_page;            // 끝 페이지
    
    // 이전,다음 버튼
    private boolean prev_page;       // 이전 버튼
    private boolean next_page;       // 다음 버튼
    
    // DB에서 사용한 변수
    private int start_index;
    
    
    
    public int getPer_page_post() {
		return per_page_post;
	}

	public void setPer_page_post(int per_page_post) {
		this.per_page_post = per_page_post;
	}

	public int getPer_block_page() {
		return per_block_page;
	}

	public void setPer_block_page(int per_block_page) {
		this.per_block_page = per_block_page;
	}

	public int getCur_page() {
		return cur_page;
	}

	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}

	public int getCur_block() {
		return cur_block;
	}

	public void setCur_block(int cur_block) {
		this.cur_block = cur_block;
	}

	public int getPost_cnt() {
		return post_cnt;
	}

	public void setPost_cnt(int post_cnt) {
		this.post_cnt = post_cnt;
	}

	public int getPage_cnt() {
		return page_cnt;
	}

	public void setPage_cnt(int page_cnt) {
		this.page_cnt = page_cnt;
	}

	public int getBlock_cnt() {
		return block_cnt;
	}

	public void setBlock_cnt(int block_cnt) {
		this.block_cnt = block_cnt;
	}

	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getEnd_page() {
		return end_page;
	}

	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}

	public boolean isPrev_page() {
		return prev_page;
	}

	public void setPrev_page(boolean prev_page) {
		this.prev_page = prev_page;
	}

	public boolean isNext_page() {
		return next_page;
	}

	public void setNext_page(boolean next_page) {
		this.next_page = next_page;
	}

	public int getStart_index() {
		return start_index;
	}

	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}
    
    
	public void Paging(int post_cnt, int cur_page){
          
		// Controller로 부터 게시글 수 , 현재페이지 값을 가져온다.
		setPost_cnt(post_cnt);  // 총 게시글 수
		setCur_page(cur_page);  // 현재 페이지
		
		setpage_cnt(post_cnt);  // 총 게시글로 총 페이지 수 구하기
		setblock_cnt(page_cnt); // 총 페이지수로 총 블록 수 구하기
		setblock(cur_page);     // 현재 페이지로 현재 블록 구하기
		
		setstart_index(cur_page); // 현재 페이지로 DB에서 사용할 변수 값 설정
        
        // 이전 버튼
        if( cur_block == 1) { prev_page = false; }
        else { prev_page = true; }
        
        // 다음 버튼
        if( end_page > page_cnt ) { end_page = page_cnt; next_page = false; } 
        else { next_page = true;}

    }
	
	// 총 게시글로 총 페이지 수 구하기
    public void setpage_cnt(int post_cnt) {
        this.page_cnt = (int) Math.ceil(post_cnt*1.0/per_page_post);
    }
    
    // 총 페이지수로 총 블록 수 구하기
    public void setblock_cnt(int page_cnt) {
        this.block_cnt = (int) Math.ceil(page_cnt*1.0/per_block_page);
    }
    
    // 현재 페이지로 현재 블록 구하기 + 시작,끝 페이지 구하기
    public void setblock(int cur_page){
        
    	setcur_block(cur_page);        
        this.start_page = (cur_block - 1) * per_block_page + 1;
        this.end_page = start_page + per_block_page - 1;
    }
    
    // 현재 페이지로 현재 블록 구하기
    public void setcur_block(int cur_page) {
        this.cur_block = (int)((cur_page-1)/per_block_page) + 1;
    }
    
    // 현재 페이지로 DB에서 사용할 변수 값 설정
    public void setstart_index(int cur_page) {
        this.start_index = (cur_page-1) * per_page_post;
    }
    
}
