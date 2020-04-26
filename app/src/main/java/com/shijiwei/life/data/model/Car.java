package com.shijiwei.life.data.model;

/**
 * Created by shijiwei on 2020-04-26.
 *
 * @Desc:
 */
public class Car {

    private String brand; /*品牌*/

    private int seatingCapacity; /*载客数量*/

    private String carNo; /*车牌号*/

    private String drivingNo; /*行驶证号*/

    private String VIN; /*车辆识别号 */

    private Purpose purpose; /*用途*/

    private Status status; /*状态*/


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDrivingNo() {
        return drivingNo;
    }

    public void setDrivingNo(String drivingNo) {
        this.drivingNo = drivingNo;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Purpose {

        Business("商务接待"),
        Management("经营"),
        All("全部");


        public String desc;
        Purpose(String desc) {
            this.desc = desc;
        }
    }

    public enum Status {

        Used("已派出"),
        UnUsed("未派出");

        public String desc;
        Status(String desc) {
            this.desc = desc;
        }
    }
}
