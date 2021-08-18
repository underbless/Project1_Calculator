import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Calculator extends JFrame implements ActionListener {

	private JPanel contentPane;
	JLabel lblnowCal, lblCalHis ;
	JButton btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnNum00, btnNum0,
		    btnPlus, btnMinus, btnMult, btnDivide, btnPow_Paren, btnCancel, btnMode, btnEqual, btnDot, btnBack;
	String result = "" ;	// 누적 계산중인 값들 저장 .
	String CalHis = "" ;	// 연산자 이전에 수행한 연산 저장.
	String nowCal = "" ;	// 현재 계산 값들 저장. 
	String operand[] = { " ", " " } ;
	String operator = "" ;
	int what_paren = 0 ; 
	
	ArrayList<String> stack = new ArrayList<String>() ;
	ArrayList<String> opt = new ArrayList<String>() ;
	
	boolean mode = true ; //true : mode1 , false : mode2.
	//mode1 = 들어오는 연속된 2개 계산.
	//mode2 = 들어오는 한 줄을 다 입력받아서 한번에 계산.
	
	//계산기 모습 그리기. 
	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancel = new JButton("C");
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(12, 86, 60, 60);
		btnCancel.addActionListener(this);
		btnCancel.setOpaque(true);
		contentPane.add(btnCancel);
		
		btnBack = new JButton("<");
		btnBack.setForeground(new Color(0, 128, 0));
		btnBack.setBounds(228, 14, 60, 60);
		btnBack.addActionListener(this) ;
		btnBack.setOpaque(true);
		contentPane.add(btnBack);
		
		btnPow_Paren = new JButton("^");
		btnPow_Paren.setForeground(new Color(0, 128, 0));
		btnPow_Paren.setBounds(156, 86, 60, 60);
		btnPow_Paren.addActionListener(this) ;
		btnPow_Paren.setOpaque(true);
		contentPane.add(btnPow_Paren);
		
		btnMode = new JButton("MODE1");
		btnMode.setForeground(new Color(0, 128, 0));
		btnMode.setBounds(84, 86, 60, 60);
		btnMode.addActionListener(this);
		btnMode.setOpaque(true);
		contentPane.add(btnMode);

		btnNum1 = new JButton("1");
		btnNum1.setBounds(12, 284, 60, 60);
		btnNum1.addActionListener(this);
		btnNum1.setOpaque(true);
		contentPane.add(btnNum1);
		
		btnNum2 = new JButton("2");
		btnNum2.setBounds(84, 284, 60, 60);
		btnNum2.addActionListener(this);
		btnNum2.setOpaque(true);
		contentPane.add(btnNum2);
		
		btnNum3 = new JButton("3");
		btnNum3.setBounds(156, 284, 60, 60);
		btnNum3.addActionListener(this);
		btnNum3.setOpaque(true);
		contentPane.add(btnNum3);
		
		btnNum4 = new JButton("4");
		btnNum4.setBounds(12, 218, 60, 60);
		btnNum4.addActionListener(this);
		btnNum4.setOpaque(true);
		contentPane.add(btnNum4);
		
		btnNum5 = new JButton("5");
		btnNum5.setBounds(84, 218, 60, 60);
		btnNum5.addActionListener(this);
		btnNum5.setOpaque(true);
		contentPane.add(btnNum5);
		
		btnNum6 = new JButton("6");
		btnNum6.setBounds(156, 218, 60, 60);
		btnNum6.addActionListener(this);
		btnNum6.setOpaque(true);
		contentPane.add(btnNum6);
		
		btnNum7 = new JButton("7");
		btnNum7.setBounds(12, 152, 60, 60);
		btnNum7.addActionListener(this);
		btnNum7.setOpaque(true);
		contentPane.add(btnNum7);
		
		btnNum8 = new JButton("8");
		btnNum8.setBounds(84, 152, 60, 60);
		btnNum8.addActionListener(this);
		btnNum8.setOpaque(true);
		contentPane.add(btnNum8);
		
		btnNum9 = new JButton("9");
		btnNum9.setBounds(156, 152, 60, 60);
		btnNum9.addActionListener(this);
		btnNum9.setOpaque(true);
		contentPane.add(btnNum9);
		
		
		btnNum00 = new JButton("00");
		btnNum00.setBounds(12, 350, 60, 60);
		btnNum00.addActionListener(this);
		btnNum00.setOpaque(true);
		contentPane.add(btnNum00);
		
		btnNum0 = new JButton("0");
		btnNum0.setBounds(84, 350, 60, 60);
		btnNum0.addActionListener(this);
		btnNum0.setOpaque(true);
		contentPane.add(btnNum0);
		
		btnDot = new JButton(".");
		btnDot.setBounds(156, 350, 60, 60);
		btnDot.addActionListener(this);
		btnDot.setOpaque(true);
		contentPane.add(btnDot);
		
		
		btnPlus = new JButton("+");
		btnPlus.setForeground(new Color(0, 128, 0));
		btnPlus.setBounds(228, 284, 60, 60);
		btnPlus.addActionListener(this);
		btnPlus.setOpaque(true);
		contentPane.add(btnPlus);
		
		btnMinus = new JButton("-");
		btnMinus.setForeground(new Color(0, 128, 0));
		btnMinus.setBounds(228, 218, 60, 60);
		btnMinus.addActionListener(this);
		btnMinus.setOpaque(true);
		contentPane.add(btnMinus);
		
		btnMult = new JButton("*");
		btnMult.setForeground(new Color(0, 128, 0));
		btnMult.setBounds(228, 152, 60, 60);
		btnMult.addActionListener(this);
		btnMult.setOpaque(true);
		contentPane.add(btnMult);
		
		btnDivide = new JButton("/");
		btnDivide.setForeground(new Color(0, 128, 0));
		btnDivide.setBounds(228, 86, 60, 60);
		btnDivide.addActionListener(this);
		btnDivide.setOpaque(true);
		contentPane.add(btnDivide);
		
		btnEqual = new JButton("=");
		btnEqual.setForeground(new Color(0, 128, 0));
		btnEqual.setBounds(228, 350, 60, 60);
		btnEqual.addActionListener(this);
		btnEqual.setOpaque(true);
		contentPane.add(btnEqual);
		
		lblCalHis = new JLabel("");
		lblCalHis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalHis.setForeground(Color.BLACK);
		lblCalHis.setBackground(Color.LIGHT_GRAY);
		lblCalHis.setBounds(12, 6, 204, 30);
		contentPane.add(lblCalHis);
		
		lblnowCal = new JLabel("0");
		lblnowCal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnowCal.setForeground(Color.BLACK);
		lblnowCal.setBackground(Color.LIGHT_GRAY);
		lblnowCal.setBounds(12, 38, 204, 30);
		contentPane.add(lblnowCal);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String pressButton = e.getActionCommand() ;
		
		// 1 ~ 9 버튼 누를 경우.
		if(pressButton.equals("1") || pressButton.equals("2") || pressButton.equals("3") ||
		   pressButton.equals("4") || pressButton.equals("5") || pressButton.equals("6") ||
		   pressButton.equals("7") || pressButton.equals("8") || pressButton.equals("9")) {
			if(nowCal.equals("0"))
				nowCal = "" ;
			nowCal += pressButton ;
			lblnowCal.setText(nowCal);
		} else if(pressButton.equals("<")) {
			if(!nowCal.equals("")) {
				nowCal = nowCal.substring(0, nowCal.length()-1);
				lblnowCal.setText(nowCal);
			}
		} else if(pressButton.equals("C")) { // C 버튼 누를 경
			reset() ;
			lblCalHis.setText("") ;
			lblnowCal.setText(nowCal);
		} else if(pressButton.equals("MODE1")) {
			mode = false ;	// 모드 변경.
			btnMode.setText("MODE2");
			btnPow_Paren.setText("( )");
			reset() ;
			lblnowCal.setText("0");
			lblCalHis.setText("");
		} else if(pressButton.equals("MODE2")) {
			mode = true ;
			btnMode.setText("MODE1");
			btnPow_Paren.setText("^");
			reset() ;
			lblnowCal.setText("0");
			lblCalHis.setText("");
		} else if(pressButton.equals("( )")) {
			if(nowCal.lastIndexOf("(") <= nowCal.lastIndexOf(")"))
				nowCal += "(" ;
			else
				nowCal += ")" ;
			what_paren++ ;
			lblnowCal.setText(nowCal);
		} else if(pressButton.equals("0") || pressButton.equals("00")) { // 0 or 00 버튼 누를 경
			if(!nowCal.equals("")) {
				if(!nowCal.equals("0")) {
					nowCal += pressButton;
					lblnowCal.setText(nowCal);
				}
			} else {
				nowCal = "0";
				lblnowCal.setText(nowCal) ;
			}	// 0, 00 구현 완
		} else if(pressButton.equals(".")) { // . 버튼 누를 경우 
			if(nowCal.equals("")) {
				nowCal= "0" + pressButton;	
				lblnowCal.setText(nowCal);
			} else {
				if(nowCal.indexOf(".") == nowCal.length() - 1) {	//마지막 문자가 . 일 경우. 
					
				} else {
					nowCal += pressButton ;
					lblnowCal.setText(nowCal);
				}
			}	// . 구현 끝 .
		} else if(pressButton.equals("=")) {	// = 버튼 누를 경우. 
			if(mode == true) {	//mode1
				if(!nowCal.equals("")) {	//현재 계산 중인 식이 있을 경우. 
					operand[1] = nowCal;	//현재 계산 중인 식을 operand에 넣는다.
				}
				if(CalHis.equals("")) {		//CalHis가 공백일 경우. 
					if(!operator.equals(" ")) {	//뒤 숫자가 공백이 아닐 경우.
						result = Cal1();
						if(result.equals("0으로 나눌 수 없습니다.")) {
							reset() ;
						} else {
							operand[0] = result;
							lblnowCal.setText(result);
						}
					}
					lblCalHis.setText(CalHis);
				} else {					//CalHis가 공백이 아니다. 
					if(operand[1].equals(" ")) {	// operand[1]이 공백이면.
						operand[1] = nowCal;
					}
					result = operand[0];
			
					if("0으로 나눌 수 없습니다.".equals(result)) {
						lblnowCal.setText(result);
						reset() ;
					} else {
						result = Cal1();
							
						if(!"0으로 나눌 수 없습니다.".equals(result)) {
							CalHis = "";
							operand[0] = result;
							lblnowCal.setText(result);
							lblCalHis.setText(CalHis);
							nowCal = "";
						} else {
							lblnowCal.setText("0으로 나눌 수 없습니다.") ;
							lblCalHis.setText("") ;
							reset() ;
						}
						System.out.println("?");
					}
				}
			} else {	//mode2
				CalHis = nowCal ;
				lblCalHis.setText(CalHis);
				result = Cal2() ;
				lblnowCal.setText(result);
				nowCal = "" ;
			}	// = 구현 끝.
		} else if(pressButton.equals("+") || pressButton.equals("-") ||	//사칙연산 누를 경우 시작. 
				  pressButton.equals("*") || pressButton.equals("/") ||
				  pressButton.equals("^")) {
			if(mode == true) { //mode1
				if(CalHis.equals("")) {		//이전 계산 기록이 없다. 
					if(nowCal.equals("")) {		//현재 계산 중인게 없다. 
						if(result.equals("")) {		//결과로 저장된 값이 없다.
							CalHis = "0" + pressButton;
							operand[0] = "0";	
						} else {					//결과로 저장 값은 있다. 
							CalHis = result + pressButton;
						}
					} else {					//현재 계산 중인게 있다.
						if(!result.equals("")) {	//결과로 저장된 값이 있다.
							CalHis = result + pressButton;
						}
						CalHis = nowCal + pressButton;
						operand[0] = nowCal;
					}
					operator = pressButton;
				} else {					//이전 계산 기록이 있다. 
					if(nowCal.equals("")) {		//현재 계산 중인게 없다.
						CalHis = CalHis.substring(0, CalHis.length()-1) + pressButton;
						operator = pressButton;
					} else {					//현재 계산 중인게 있다.
						CalHis += nowCal + pressButton;
						operand[1] = nowCal;
						result = Cal1();
						operator = pressButton;
						lblnowCal.setText(result);
						operand[0] = result;
					}
				}
				//0으로 나눌 경우.
				if(operator.equals("/") && (operand[1].equals(" 0") || operand[1].equals("0"))) {
					
				} else {
					nowCal = "";	
					lblCalHis.setText(CalHis);
				}
			} else {	//사칙연산 mode2
				int len = nowCal.length();
				
				if(nowCal.equals("")) {		//현재 계산 중인게 없다. 
					nowCal = "0" + pressButton ;
				} else if(is_operator(nowCal.substring(len-1))) {	//마지막이 연산자인 경우. 제거하고 새거 추가.
					if(nowCal.substring(len-1).equals(")"))
						nowCal = nowCal + pressButton ;
					else
						nowCal = nowCal.substring(0, len-1) + pressButton ; 
				} else {
					nowCal = nowCal + pressButton ;
				}
				lblnowCal.setText(nowCal) ; 
			}	//사칙연산 mode2 끝.
		}	//사칙연산 구현 끝.
	}
	
	public boolean is_operator(String s) {
		if(s.equals("+") || s.equals("-") || s.equals("*") ||
		   s.equals("/") || s.equals("^") || s.equals("(") ||
		   s.equals(")")) {
			return true ;
		}
		return false ;
	}
	
	public void reset() {
		//0으로 나누려 할 경우. 초기화한다.
		result = "" ;
		nowCal = "" ;
		CalHis = "" ;
		operand[0] = " " ;
		operand[1] = " " ;
		operator = " " ;
	}
	
	public void check() {
		String operand1 = "" ;
		String operand2 = "" ;
		
		if(operand[0].contains(".") && operand[0].indexOf(".") != -1) {	//제일 끝자리가 0이 아닐 때. 
			int index1 = operand[0].indexOf(".");	// 점 위치를 찾는다.

			operand1 = operand[0].substring(index1, operand[0].length());

			if(operand1.replaceAll("0", "").equals(".")){	//소수점 아래에 0밖에 없을 경우. 
				operand[0] = operand[0].substring(0, index1);
			}
		}

		if(operand[1].contains(".") && operand[1].indexOf(".") != -1) {	//제일 끝자리가 0이 아닐 때.
			int index2 = operand[1].indexOf(".");	// 점 위치를 찾는다.

			operand2 = operand[1].substring(index2, operand[1].length());

			if(operand2.replaceAll("0", "").equals(".")){	//소수점 아래에 0밖에 없을 경우. 
				operand[1] = operand[1].substring(0, index2);
			}
		}
	}
	
	public String Cal1() {
		String tot = "" ;	//계산결과. return값. 
		
		check() ;
		
		if(operator.equals("*")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) * Double.parseDouble(operand[1]));
			} else {
				if(operand[1].equals("")){
					tot = nowCal;
				} else {
					if(!result.equals("")) {
						operand[0] = result;
						check() ;
					}
					tot = "" + (Long.parseLong(operand[0]) * Long.parseLong(operand[1]));
					result = tot;
					operand[0] = result;
				}
			}
		} else if(operator.equals("+")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) + Double.parseDouble(operand[1]));
			} else {
				if(operand[1].equals("")){
					tot = nowCal;
				} else {
					if(!result.equals("")) {
						operand[0] = result;
						check() ;
					}
					tot = "" + (Long.parseLong(operand[0]) + Long.parseLong(operand[1]));
					result = tot;
					operand[0] = result;
				}
			}
		} else if(operator.equals("-")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) - Double.parseDouble(operand[1]));
			} else {
				if("".equals(operand[1])){
					tot = nowCal;
				} else {
					if(!"".equals(result)) {
						operand[0] = result;
						check() ;
					}
					tot = "" + (Long.parseLong(operand[0]) - Long.parseLong(operand[1]));
					result = tot;
					operand[0] = result;
				}
			}
		} else if(operator.equals("^")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + Math.pow(Double.parseDouble(operand[0]), Double.parseDouble(operand[1]));
			} else {
				if("".equals(operand[1])){
					tot = nowCal;
				} else {
					if(!"".equals(result)) {
						operand[0] = result;
						check() ;
					}
					tot = "" + Math.pow(Long.parseLong(operand[0]), Long.parseLong(operand[1]));
					result = tot;
					operand[0] = result;
				}
			}
		} else if(operator.equals("/")) {
			operand[1].trim();
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) / Double.parseDouble(operand[1]));
			} else {
				if(operand[1].equals("0")) {
					tot = "0으로 나눌 수 없습니다.";
					nowCal = "";
				} else {
					if("".equals(operand[1])){
						tot = nowCal;
					} else {
						if(!"".equals(result)) {
							operand[0] = result;
							check() ;
						}
						tot = "" + (Double.parseDouble(operand[0]) / Double.parseDouble(operand[1]));

						if(tot.indexOf(".")!=-1) {
							int index3 = tot.indexOf(".");
							String testNum3 = tot.substring(index3, tot.length());
							if(testNum3.replaceAll("0", "").equals(".")){
								tot = tot.substring(0, index3);
							}
						}
						result = tot;
						operand[0] = result;
					}
				}
			}
		}
		return tot;
	}
	
	public String Cal2_5() {
		String tot = "" ;	//계산결과. return값. 
		
		check() ;
		
		if(operator.equals("*")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) * Double.parseDouble(operand[1]));
			} else {
				tot = "" + (Long.parseLong(operand[0]) * Long.parseLong(operand[1]));
			}
		} else if(operator.equals("+")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) + Double.parseDouble(operand[1]));
			} else { 
				tot = "" + (Long.parseLong(operand[0]) + Long.parseLong(operand[1]));
			}
		} else if(operator.equals("-")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) - Double.parseDouble(operand[1]));
			} else {
				tot = "" + (Long.parseLong(operand[0]) - Long.parseLong(operand[1]));
			}
		} else if(operator.equals("^")) {
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + Math.pow(Double.parseDouble(operand[0]), Double.parseDouble(operand[1]));
			} else {
				tot = "" + Math.pow(Long.parseLong(operand[0]), Long.parseLong(operand[1]));
			}
		} else if(operator.equals("/")) {
			operand[1].trim();
			if(operand[0].indexOf(".")!=-1 || operand[1].indexOf(".")!=-1) {
				tot = "" + (Double.parseDouble(operand[0]) / Double.parseDouble(operand[1]));
			} else {
				if(operand[1].equals("0")) {
					tot = "0으로 나눌 수 없습니다.";
					nowCal = "";
				} else {
					tot = "" + (Double.parseDouble(operand[0]) / Double.parseDouble(operand[1]));
					if(tot.indexOf(".")!=-1) {
						int index3 = tot.indexOf(".");
						String testNum3 = tot.substring(index3, tot.length());
						if(testNum3.replaceAll("0", "").equals(".")){
							tot = tot.substring(0, index3);
						}
					}
					result = tot;
					operand[0] = result;
				}
			}
		}
		return tot;
	}
	
	public String Cal2() {
		String total = "" ; 
		String ch = "" ;
		String adop = "" ;
		postFix() ;
		
		while(stack.size() != 0) {
			ch = stack.get(0) ;
			stack.remove(0) ;
			if(!is_operator(ch)) {
				opt.add(ch) ;
			} else {
				operand[0] = opt.get(opt.size() - 2) ;
				operand[1] = opt.get(opt.size() - 1) ;
				opt.remove(opt.size() - 1);
				opt.remove(opt.size() - 1);
				operator = ch ;
				System.out.println(operand[0] + operator + operand[1]) ;
				adop = Cal2_5() ;
				System.out.println(adop) ;
				opt.add(adop) ;
			}
			System.out.println("stack : " + stack) ;
			System.out.println("opt : " + opt) ;
		}
		total = opt.get(0) ;
		opt.clear() ;
		
		return total ;
	}
	
	
	//스택 만들기. 
	public void postFix() {	// 0 : ( ), 1 : + -, 2 : * /
		int stt_pos = 0 ; 
		int end_pos = 0 ;
		String star = "*" ;
		String ch = "" ;
		String pre_ch = "" ;
		String numb = "" ;
		
		while(nowCal.length() > end_pos) {	
			ch = String.valueOf(nowCal.charAt(end_pos));
			if(is_operator(ch) == true) {	//연산자면 실행한다. 아니면 pass
				pre_ch = String.valueOf(nowCal.charAt(end_pos - 1)) ;
				if(ch.equals("(") && is_operator(pre_ch)) {
					// 여는 소괄호"(" 앞에 operator가 있는 경우. 숫자를 저장하지 않는다.
					opt.add(ch) ;
					end_pos++ ;
					stt_pos = end_pos ; 
					continue ; 
				} else {	// 그 외의 경우. 
					numb = nowCal.substring(stt_pos, end_pos) ;
					stack.add(numb) ;
					stt_pos = end_pos + 1 ;

					if(!ch.equals("(") && opt.size() == 0) {	//연산자 담아두는 곳이 비었으면 바로 넣기.
						opt.add(ch) ;
					} else {	//아니면 들어있는 연산자 확인하고 넣는다. 
						if(ch.equals("(")) {
							opt.add(star) ;
							opt.add(ch) ;
						} else if(ch.equals(")")) {
							while(opt.size() != 0) {
								if(opt.get(opt.size() - 1).equals("(")) {
									opt.remove(opt.size() - 1) ;
									break ;
								} else {
									stack.add(opt.get(opt.size() - 1)) ;
									opt.remove(opt.size() - 1) ;
								}
							}
						} else if(imp_lvl(opt.get(opt.size() - 1)) < imp_lvl(ch)) {
							opt.add(ch) ;
						} else {
							while(imp_lvl(opt.get(opt.size() - 1)) >= imp_lvl(ch)) {
								stack.add(opt.get(opt.size() - 1)) ;
								opt.remove(opt.size() - 1) ;
								if(opt.size() == 0)
									break ;
							}
							opt.add(ch) ;
						}
					}
				}
			}
			end_pos++ ; 
		}
		if(ch == ")") {
			
		} else {
			stack.add(nowCal.substring(stt_pos, end_pos)) ;
		}
		while(opt.size() != 0) {
			if(opt.get(opt.size() - 1).equals("(")) {
				opt.remove(opt.size() - 1) ;
				break ;
			} else {
				stack.add(opt.get(opt.size() - 1)) ;
				opt.remove(opt.size() - 1) ;
			}
		}
		while(stack.contains(""))
			stack.remove("") ;
	}
	
	public int imp_lvl(String s) {
		if(s.equals("+") || s.equals("-"))
			return 1 ;
		else if(s.equals("*") || s.equals("/"))
			return 2 ;
		return 0 ;
	}
	
	public static void main(String[] args) {
		
		Calculator frame = new Calculator();
		frame.setVisible(true);
	}
}
