package com.tv.xeeng.reporttool.dao;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tv.xeeng.reporttool.beans.QuestionBean;
import com.tv.xeeng.reporttool.crypto.Hex;
import com.tv.xeeng.reporttool.util.Base64;

public class QuestionDAO {

    // getList
    public List<QuestionBean> getDataByNumRow(int pageCurrent, String keyword) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        int rowNumDisplay = 20;
        int rowIdFirst = ((pageCurrent - 1) * rowNumDisplay) + 1;
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        List<QuestionBean> questionList = new ArrayList<QuestionBean>();
        String sql1 = "SELECT QuestionId = COUNT(QuestionId) FROM Question";


        String sql2 = "Select QuestionId, detail, choix1, choix2, choix3, choix4, answer, levelID, domainID from  (select *,row_number() over (order by QuestionId DESC) as r from Question ) data_row where r >=? and r <= ?";

        if (keyword != null && keyword != "") {
            sql1 += " WHERE detail LIKE '%{encodedKeyword}%'".replace("{encodedKeyword}", getEncodeKeyword(keyword));

//            sql2 += " AND detail LIKE '%{encodedKeyword}%'".replace("{encodedKeyword}", getEncodeKeyword(keyword));

            sql2 = "Select QuestionId, detail, choix1, choix2, choix3, choix4, answer, levelID, domainID from  (select *,row_number() over (order by QuestionId DESC) as r from Question" + " WHERE detail LIKE '%{encodedKeyword}%'".replace("{encodedKeyword}", getEncodeKeyword(keyword)) + " ) data_row where r >=? and r <= ?";

        }

        try {
            conn = DBPoolConnection.getConnection();
            psmt = conn.prepareStatement(sql1);
            rs = psmt.executeQuery();
            if (rs.next()) {
                totalRc = rs.getInt(1);
                System.out.println("Tong ban ghi: " + totalRc);
                totalPage = totalRc / rowNumDisplay;
                System.out.println("Tong trang: " + totalPage);
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
                if (totalPage > 0) {
                    psmt = conn.prepareStatement(sql2);
                    psmt.setInt(1, rowIdFirst);
                    psmt.setInt(2, rowIdLast);
                    rs = psmt.executeQuery();

                    while (rs.next()) {
                        QuestionBean questionBean = new QuestionBean();
                        questionBean.setQuestionId(rs.getInt("QuestionId"));
                        String detai = rs.getString("detail");
                        try {
                            byte[] dataByte = Hex.decode(detai);
                            detai = new String(Base64.decode(new String(
                                    dataByte)), "UTF-8");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        questionBean.setDetail(detai);
                        questionBean.setChoix1(rs.getString("choix1"));
                        questionBean.setChoix2(rs.getString("choix2"));
                        questionBean.setChoix3(rs.getString("choix3"));
                        questionBean.setChoix4(rs.getString("choix4"));
                        questionBean.setAnswer(rs.getInt("answer"));
                        questionBean.setLevelId(rs.getInt("levelID"));
                        questionBean.setDomainId(rs.getInt("domainID"));
                        questionBean.setTotalPage(totalPage);
                        questionBean.setTotalRecord(totalRc);

                        questionList.add(questionBean);
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return questionList;
    }

    // add a new row data
    public int addQuestionBean(QuestionBean qBean) {
        int result = 0;
        String sql = "INSERT INTO Question VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            String detail = qBean.getDetail();
            System.out.print(detail);
            String encodeBase64 = Base64.encode(detail.getBytes(Charset.forName("UTF-8")));
            byte[] encodeHex = Hex.encode(encodeBase64.getBytes(Charset.forName("UTF-8")));

            psmt.setString(1, new String(encodeHex));
            psmt.setString(2, qBean.getChoix1());
            psmt.setString(3, qBean.getChoix2());
            psmt.setString(4, qBean.getChoix3());
            psmt.setString(5, qBean.getChoix4());
            psmt.setInt(6, qBean.getAnswer());
            psmt.setInt(7, qBean.getLevelId());
            psmt.setInt(8, qBean.getDomainId());

            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    // get a object by Colum Value
    public QuestionBean getQuestionById(int questionId) {
        QuestionBean qBean = new QuestionBean();
        try {
            Connection conn = DBPoolConnection.getConnection();
            String sql = "SELECT * FROM Question WHERE QuestionId = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, questionId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                qBean.setQuestionId(rs.getInt("QuestionId"));
                String detai = rs.getString("detail");
                try {
                    byte[] dataByte = Hex.decode(detai);
                    detai = new String(Base64.decode(new String(
                            dataByte)), "UTF-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                qBean.setDetail(detai);
                qBean.setChoix1(rs.getString("choix1"));
                qBean.setChoix2(rs.getString("choix2"));
                qBean.setChoix3(rs.getString("choix3"));
                qBean.setChoix4(rs.getString("choix4"));
                qBean.setAnswer(rs.getInt("answer"));
                qBean.setLevelId(rs.getInt("levelID"));
                qBean.setDomainId(rs.getInt("domainID"));

            }

            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return qBean;
    }

    // update a row data
    public int updateAQuestion(QuestionBean qBean) {
        int result = 0;
        String sql = "UPDATE Question SET detail = ?, choix1 = ?, choix2 = ?, choix3 = ? , choix4 = ?, answer = ?, levelID = ?, domainID = ? WHERE QuestionId= ?";
        Connection conn;
        try {
            conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);

            String detail = qBean.getDetail();
            String encodeBase64 = Base64.encode(detail.getBytes(Charset.forName("UTF-8")));
            byte[] encodeHex = Hex.encode(encodeBase64.getBytes(Charset.forName("UTF-8")));

            psmt.setString(1, new String(encodeHex));
            psmt.setString(2, qBean.getChoix1());
            psmt.setString(3, qBean.getChoix2());
            psmt.setString(4, qBean.getChoix3());
            psmt.setString(5, qBean.getChoix4());
            psmt.setInt(6, qBean.getAnswer());
            psmt.setInt(7, qBean.getLevelId());
            psmt.setInt(8, qBean.getDomainId());
            psmt.setInt(9, qBean.getQuestionId());

            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // delete a row data
    public int deleteAQuestion(int questionId) {
        int result = 0;
        String sql = "DELETE FROM Question WHERE QuestionId = ?";
        try {
            Connection conn = DBPoolConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, questionId);
            result = psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
//
//    private static byte[] truncateBase64(String b64) {
//        byte[] bytes = b64.getBytes(Charset.forName("UTF-8"));
//        byte[] truncatedKeyword = new byte[(bytes.length / 3) * 3];
//        for (int i = 0; i < truncatedKeyword.length; i++) {
//            truncatedKeyword[i] = bytes[i];
//        }
//
//        return truncatedKeyword;
//    }

    private static String getEncodeKeyword(String keyword) {
        String base64ed = Base64.encode(keyword.getBytes(Charset.forName("UTF-8")));
        base64ed = base64ed.replace("=", "");

        // truncate base64ed to make the length of it is multiplier of 4 (= block size of base64)
        base64ed = base64ed.substring(0, (base64ed.length() / 4) * 4);

        return new String(Hex.encode(base64ed.getBytes()));
    }

    // =========Unit-test==========//

    public static void main(String[] args) {
//		 QuestionBean qBean = new QuestionBean();
//		 qBean.setDetail("Bạn thường ăn cơm với gì ?");
//		 qBean.setChoix1("A");
//		 qBean.setChoix2("B");
//		 qBean.setChoix3("C");
//		 qBean.setChoix4("D");
//		 qBean.setAnswer(1);
//		 qBean.setLevelId(2);
//		 qBean.setDomainId(3);
//		 
//		 int rs = new QuestionDAO().addQuestionBean(qBean);
//		 System.out.print("rs:" + rs);

//
//		 try {
//			String detail = "Nguyễn Văn Hải";
//			String encodeBase64 = Base64.encode(detail.getBytes());
//			
//			
//			System.out.print(encodeBase64);
//			byte[] encodeHex = Hex.encode(encodeBase64.getBytes());
//			System.out.print("encodeHex: "+ new String(encodeHex));
//						
//            byte[] decodeHex = Hex.decode("516a39754948526f507a39755a79412f6269426a50323067646a39704947667350773d3d".getBytes());
//            System.out.print("\ndeencodeHex: "+ new String(decodeHex));
//
//			String datavalue;
//			try {
//				datavalue = new String(Base64.decode(new String(decodeHex)));
//				System.out.print("input value: "+ datavalue.);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//            
////            
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

    }

}
