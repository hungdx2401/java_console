/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.java.seeds;

import console.java.models.DAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DongHo
 */
public class ProductSeed {

     public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Xóa dữ liệu cũ
	  stt.execute("TRUNCATE TABLE products;");
	  // Thêm dữ liệu mẫu
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP001','LOLA LONGER LENGTH MIDI SKIRT','"
		  + "Steal the spotlight this season in micro minis, of-the-moment midis and floor-sweeping maxi skirts. Whether you stick to separates or go matchy-matchy in a co-ord crop top, a skirt is the starting point to any stellar party look. For the new season, eveningwear gets earthy - take to the dance floor in decorative floral embroidery, or work whimsical nature motifs and give off a garden party vibe for going out."
		  + "', '100', '100', '10', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP002','ROBYN TEXTURED MIDI SKIRT','"
		  + "Beat the winter blues with bodycon skirts in bright primary colours, or play with the punchy palette in pleated skirts to channel a cheerleader vibe. Continuing the sporty theme, midi skirts come with stripe trims and ribbed finishes so you can work off-court cool on off-duty days. Soften up sporty and wear an A-line skirt with 60s-inspired white tights to take your style to new heights."
		  + "', '90', '90', '9', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP003','BOUTIQUE KIZZY CROCHET LACE SKIRT','"
		  + "Streamline your style in a sleek skirt. Take your style to new lengths, whether it’s micro minis or split side maxis, or flirt with the feminine side of fashion in a form fitting pencil skirt. Our style secret? ‘Shh’…sheer panels and high shine hues in candy colours and molten metallics. Keep it classy in a cami top and super strappy heels and let the skirt do the talking."
		  + "', '80', '90', '8', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP004','NAZIA RIBBED ZIP BACK MIDI SKIRT','"
		  + "Beat the winter blues with bodycon skirts in bright primary colours, or play with the punchy palette in pleated skirts to channel a cheerleader vibe. Continuing the sporty theme, midi skirts come with stripe trims and ribbed finishes so you can work off-court cool on off-duty days. Soften up sporty and wear an A-line skirt with 60s-inspired white tights to take your style to new heights."
		  + "', '100', '100', '10', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP005','THEA POLKA DOT MINI SKIRT','"
		  + "Lorem ipsum dolor sit atmet Steal the spotlight this season in micro minis, of-the-moment midis and floor-sweeping maxi skirts. Whether you stick to separates or go matchy-matchy in a co-ord crop top, a skirt is the starting point to any stellar party look. For the new season, eveningwear gets earthy - take to the dance floor in decorative floral embroidery, or work whimsical nature motifs and give off a garden party vibe for going out."
		  + "', '100', '100', '10', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP006','SASHA OVERSIZED V NECK JUMPER','"
		  + "Our easy- layer knits will have your nearly- spring wardrobe sorted! The coatigan is our fave hybrid to keep on hand, while sheer mesh knits and cropped jumpers are lifesavers for no- drama dressing! Max out your style in longline cardigans - perfect for throwing on with skinny jeans and ankle boots by day!"
		  + "', '100', '100', '10', '2', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP007','LOLA LONGER LENGTH MIDI SKIRT','"
		  + "Steal the spotlight this season in micro minis, of-the-moment midis and floor-sweeping maxi skirts. Whether you stick to separates or go matchy-matchy in a co-ord crop top, a skirt is the starting point to any stellar party look. For the new season, eveningwear gets earthy - take to the dance floor in decorative floral embroidery, or work whimsical nature motifs and give off a garden party vibe for going out."
		  + "', '100', '100', '10', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP008','ROBYN TEXTURED MIDI SKIRT','"
		  + "Beat the winter blues with bodycon skirts in bright primary colours, or play with the punchy palette in pleated skirts to channel a cheerleader vibe. Continuing the sporty theme, midi skirts come with stripe trims and ribbed finishes so you can work off-court cool on off-duty days. Soften up sporty and wear an A-line skirt with 60s-inspired white tights to take your style to new heights."
		  + "', '90', '90', '9', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP009','BOUTIQUE KIZZY CROCHET LACE SKIRT','"
		  + "Streamline your style in a sleek skirt. Take your style to new lengths, whether it’s micro minis or split side maxis, or flirt with the feminine side of fashion in a form fitting pencil skirt. Our style secret? ‘Shh’…sheer panels and high shine hues in candy colours and molten metallics. Keep it classy in a cami top and super strappy heels and let the skirt do the talking."
		  + "', '80', '90', '8', '1', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP010','NAZIA RIBBED ZIP BACK MIDI SKIRT','"
		  + "Beat the winter blues with bodycon skirts in bright primary colours, or play with the punchy palette in pleated skirts to channel a cheerleader vibe. Continuing the sporty theme, midi skirts come with stripe trims and ribbed finishes so you can work off-court cool on off-duty days. Soften up sporty and wear an A-line skirt with 60s-inspired white tights to take your style to new heights."
		  + "', '100', '100', '10', '2', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP011','THEA POLKA DOT MINI SKIRT','"
		  + "Lorem ipsum dolor sit atmet Steal the spotlight this season in micro minis, of-the-moment midis and floor-sweeping maxi skirts. Whether you stick to separates or go matchy-matchy in a co-ord crop top, a skirt is the starting point to any stellar party look. For the new season, eveningwear gets earthy - take to the dance floor in decorative floral embroidery, or work whimsical nature motifs and give off a garden party vibe for going out."
		  + "', '100', '100', '10', '2', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  stt.execute("INSERT INTO products "
		  + "(barcode, name, description, quantity, price, discount, category_id, status, created_at, updated_at) "
		  + "VALUES "
		  + "('SP012','SASHA OVERSIZED V NECK JUMPER','"
		  + "Our easy- layer knits will have your nearly- spring wardrobe sorted! The coatigan is our fave hybrid to keep on hand, while sheer mesh knits and cropped jumpers are lifesavers for no- drama dressing! Max out your style in longline cardigans - perfect for throwing on with skinny jeans and ankle boots by day!"
		  + "', '100', '100', '10', '2', '1', '2017-03-10 00:00:00', '2017-03-10 00:00:00');");
	  System.out.println("-------- Chèn thành công (12) sản phẩm --------");
     }
}
