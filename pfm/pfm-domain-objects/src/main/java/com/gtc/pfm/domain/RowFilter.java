/**
 * 
 */

package com.gtc.pfm.domain;

/**
 * This class defines a range with first row and last row. It is used in queries
 * returning lists. Default values are first row = 0, last row = 10. Also it provides an option "apply" in order to discard
 * row filtering.By default this option is "true"
 * 
 * @author stanriku
 */
public class RowFilter {

    private int firstRow = 0;

    private int lastRow = 10;
    
    private boolean apply = true;

    /**
     * 
     */
    public RowFilter() {

    }

    /**
     * @param firstRow
     * @param lastRow
     */
    public RowFilter(int firstRow, int lastRow) {
        super();
        this.firstRow = firstRow;
        this.lastRow = lastRow;
    }
    
    
    /**
     * @param firstRow
     * @param lastRow
     * @param apply
     */
    public RowFilter(int firstRow, int lastRow, boolean apply) {
        super();
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.apply = apply;
    }
    
    /**
     * @return the apply
     */
    public boolean isApply() {
        return apply;
    }

    /**
     * @param apply the apply to set
     */
    public void setApply(boolean apply) {
        this.apply = apply;
    }

    /**
     * @return the firstRow
     */
    public int getFirstRow() {
        return firstRow;
    }

    /**
     * @param firstRow the firstRow to set
     */
    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    /**
     * @return the lastRow
     */
    public int getLastRow() {
        return lastRow;
    }

    /**
     * @param lastRow the lastRow to set
     */
    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + firstRow;
        result = prime * result + lastRow;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RowFilter)) {
            return false;
        }
        RowFilter other = (RowFilter) obj;
        if (firstRow != other.firstRow) {
            return false;
        }
        if (lastRow != other.lastRow) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RowFilter [firstRow=");
        builder.append(firstRow);
        builder.append(", lastRow=");
        builder.append(lastRow);
        builder.append(", apply=");
        builder.append(apply);
        builder.append("]");
        return builder.toString();
    }


}
