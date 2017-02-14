package ar.com.uade.pfi.model.experimet;

public class StatusServiceModel {
	boolean _lock = false;
	Integer _progress = 0, _total = 0;
	
	public boolean isLock() {
		return _lock;
	}
	public void setLock(boolean lock) {
		this._lock = lock;
	}
	public Integer getProgress() {
		return _progress;
	}
	public void setProgress(Integer progress) {
		this._progress = progress;
	}
	public Integer getTotal() {
		return _total;
	}
	public void setTotal(Integer total) {
		this._total = total;
	}	
}
