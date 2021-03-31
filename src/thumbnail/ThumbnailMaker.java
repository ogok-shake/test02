package thumbnail;
import java.awt.image.BufferedImage; 
import java.io.File;
import javax.imageio.ImageIO; 
import org.imgscalr.Scalr;

public class ThumbnailMaker{

	public ThumbnailMaker(String filePath, String fileName, String fileExt){
		try {
			makeThumbnail(filePath, fileName, fileExt);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
	private void makeThumbnail(String filePath, String fileName, String fileExt) throws Exception {
		// ����� �������Ϸκ��� BufferedImage ��ü�� �����մϴ�. 
		BufferedImage srcImg = ImageIO.read(new File(filePath+fileName)); 
		// ������� �ʺ�� ���� �Դϴ�. 
		int dw = 250, dh = 250; 
		// ���� �̹����� �ʺ�� ���� �Դϴ�. 
		int ow = srcImg.getWidth(); 
		int oh = srcImg.getHeight(); 
		// ���� �ʺ� �������� �Ͽ� ������� ������ ���̸� ����մϴ�. 
		int nw = ow; 
		int nh = (ow * dh) / dw; 
		// ���� ���̰� �������� ���ٸ� crop�� �ȵǹǷ� 
		// ���� ���̸� �������� ������� ������ �ʺ� ����մϴ�. 
		if(nh > oh) { 
			nw = (oh * dw) / dh; 
			nh = oh; 
		} 
		// ���� ũ��� �����̹����� ������� crop �մϴ�. 
		BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh); 
		// crop�� �̹����� ������� �����մϴ�. 
		BufferedImage destImg = Scalr.resize(cropImg, dw, dh); 
		// ������� �����մϴ�. �̹��� �̸� �տ� "THUMB_" �� �ٿ� ǥ���߽��ϴ�. 
		String thumbName = filePath+"THUMB_" + fileName; 
		File thumbFile = new File(thumbName); 
		ImageIO.write(destImg, fileExt, thumbFile); 
		
		
		}
	}

	

