package CourseSchedule;

import com.mindfusion.common.*;
import com.mindfusion.common.ChangeListener;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Colors;
import com.mindfusion.drawing.awt.AwtImage;
import com.mindfusion.scheduling.*;
import com.mindfusion.drawing.Color;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.*;
import com.mindfusion.scheduling.model.ItemEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame implements WindowListener{

    private JCheckBox DBMS;
    private JCheckBox OS;
    private JCheckBox JAVA;
    private JCheckBox DC;
	private JCheckBox LA;
	private JCheckBox UNIX;
	private JCheckBox REST;
	public static MainWindow window = null;
    private Choice teachers;
    private AwtCalendar calendar;
    ArrayList<Contact> contactsList;

    public MainWindow() {

      addWindowListener(this);  
	  setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("SIDDAGANGA INSTITUTE OF TECHNOLOGY");

        setMinimumSize(new Dimension(800, 600));

        contactsList = new ArrayList<Contact>();

        Container container = this.getContentPane();
        SpringLayout springLayout = new SpringLayout();
        container.setLayout(springLayout);

        teachers = new Choice();
        container.add(teachers);
		
		
        DBMS = new JCheckBox("DBMS");
        DBMS.setSelected(true);

        DBMS.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });
        container.add(DBMS);
		
		REST = new JCheckBox("REST");
        REST.setSelected(true);

        REST.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });
        container.add(REST);

        DC = new JCheckBox("DC");

        DC.setSelected(true);
        DC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });

        container.add(DC);

        JAVA = new JCheckBox("JAVA");
        JAVA.setSelected(true);
        JAVA.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });

        container.add(JAVA);

        OS = new JCheckBox("OS");

        OS.setSelected(true);
        OS.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });

        container.add(OS);
		
		LA = new JCheckBox("LA");

        LA.setSelected(true);
        LA.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });

        container.add(LA);
		
		UNIX = new JCheckBox("UNIX");

        UNIX.setSelected(true);
        UNIX.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                checkBoxChanged(e);
            }
        });

        container.add(UNIX);

        springLayout.putConstraint(SpringLayout.SOUTH, OS, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, OS, 5, SpringLayout.EAST, DBMS);

        springLayout.putConstraint(SpringLayout.SOUTH, DC, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, DC, 5, SpringLayout.EAST, OS);

        springLayout.putConstraint(SpringLayout.SOUTH, JAVA, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, JAVA, 5, SpringLayout.EAST, DC);
		
		springLayout.putConstraint(SpringLayout.SOUTH, LA, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, LA, 5, SpringLayout.EAST, JAVA);
		
		springLayout.putConstraint(SpringLayout.SOUTH, UNIX, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, UNIX, 5, SpringLayout.EAST, LA);
		
		springLayout.putConstraint(SpringLayout.SOUTH, REST, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, REST, 5, SpringLayout.EAST, UNIX);
		

        JLabel label = new JLabel("Select a teacher:");
        container.add(label);

        calendar = new AwtCalendar();
        calendar.beginInit();
        calendar.setCurrentView(CalendarView.Timetable);
        calendar.setTheme(ThemeType.Vista);
        calendar.setCustomDraw(CustomDrawElements.TimetableItem);
        calendar.setGroupType(GroupType.FilterByContacts);

  

        calendar.getTimetableSettings().getCellStyle().setBorderBottomColor(new Color(169, 169, 169));
        calendar.getTimetableSettings().getCellStyle().setBorderBottomWidth(1);
        calendar.getTimetableSettings().getCellStyle().setBorderLeftColor(new Color(169, 169, 169));
        calendar.getTimetableSettings().getCellStyle().setBorderLeftWidth(1);
        calendar.getTimetableSettings().getCellStyle().setBorderRightColor(new Color(169, 169, 169));
        calendar.getTimetableSettings().getCellStyle().setBorderRightWidth(1);
        calendar.getTimetableSettings().getCellStyle().setBorderTopColor(new Color(169, 169, 169));
        calendar.getTimetableSettings().getCellStyle().setBorderTopWidth(1);
        calendar.getTimetableSettings().getCellStyle().setHeaderTextShadowOffset(0);
        calendar.getTimetableSettings().getCellStyle().setHeaderTextShadowStyle(ShadowStyle.None);
        calendar.getTimetableSettings().getDates().clear();

        for (int i = 0; i < 7; i++)
            calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(i-1));

        calendar.getTimetableSettings().setItemOffset(30);
        calendar.getTimetableSettings().setShowItemSpans(false);
        calendar.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
        calendar.getTimetableSettings().setVisibleColumns(7);
        calendar.endInit();

        springLayout.putConstraint(SpringLayout.EAST, calendar, 0, SpringLayout.EAST, container);
        springLayout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, container);
        springLayout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.SOUTH, calendar, -35, SpringLayout.NORTH, DBMS);

        springLayout.putConstraint(SpringLayout.WEST, teachers, 5, SpringLayout.EAST, label);
        springLayout.putConstraint(SpringLayout.SOUTH, teachers, -5, SpringLayout.NORTH, DBMS);

        springLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.SOUTH, label, -5, SpringLayout.NORTH, DBMS);

        springLayout.putConstraint(SpringLayout.SOUTH, DBMS, -5, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.WEST, DBMS, 5, SpringLayout.WEST, container);

       calendar.setEnableDragCreate(true);

        calendar.addCalendarListener(new CalendarAdapter() {
           public void draw(DrawEvent e) {
                onCalendarDraw(e);
            }
            public void itemCreated(ItemEvent e) {
                onItemCreated(e);
            }

            public void itemCreating(ItemConfirmEvent e) {
                onCalendarItemCreating(e);
            }

        });

        initializeContacts();

        container.add(calendar);
    }

    private void checkBoxChanged(java.awt.event.ItemEvent e) {
        boolean addItems = true;
  
  
        if (e.getStateChange() == java.awt.event.ItemEvent.DESELECTED) {
            addItems = false;
        }

        Object source = e.getItemSelectable();

        if (source == DBMS) {

            for (Contact c : contactsList) {
                if (c.getId().startsWith("DBMS")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }
        } else if (source == DC) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("DC")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }
        } else if (source == JAVA) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("JAVA")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }

                }
            }
        }
        else if (source == OS) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("OS")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }

        }else if (source == LA) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("LA")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }

        }else if (source == UNIX) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("UNIX")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }

        }else if (source == REST) {
            for (Contact c : contactsList) {
                if (c.getId().startsWith("REST")) {

                    if (addItems) {
                        calendar.getContacts().add(c);
                        teachers.add(c.getName());
                    } else {
                        calendar.getContacts().remove(c);
                        teachers.remove(c.getName());
                    }
                }
            }

        }
    }


    private void initializeContacts() {

        Contact contact = new Contact();
        contact.setId("JAVA_MW");
        contact.setName("Sharmila S.P.");
        teachers.add(contact.getName());
        calendar.getContacts().add(contact);
        contactsList.add(contact);


        contact = new Contact();
        contact.setId("DC_DR");
        contact.setName("Jagadamba G.");
        calendar.getContacts().add(contact);
        teachers.add(contact.getName());
        contactsList.add(contact);

     

        contact = new Contact();
        contact.setId("DBMS_RS");
        contact.setName("Dr. Kavitha H");
        calendar.getContacts().add(contact);
        teachers.add(contact.getName());
        contactsList.add(contact);

    

        contact = new Contact();
        contact.setId("OS_FT");
        contact.setName("Subha C");
        calendar.getContacts().add(contact);
        teachers.add(contact.getName());
        contactsList.add(contact);
		
		contact = new Contact();
        contact.setId("LA_FT");
        contact.setName("P.S.K Reddy");
        calendar.getContacts().add(contact);
        teachers.add(contact.getName());
        contactsList.add(contact);
		
		contact = new Contact();
        contact.setId("UNIX_FT");
        contact.setName("Dr. Purothit");
        calendar.getContacts().add(contact);
        teachers.add(contact.getName());
        contactsList.add(contact);
		
		contact = new Contact();
        contact.setId("REST_FT");
        contact.setName("SELF");
        teachers.add(contact.getName());
        calendar.getContacts().add(contact);
        contactsList.add(contact);

      

     
    }


    protected void onCalendarDraw(DrawEvent e) {
        if (e.getElement() == CustomDrawElements.TimetableItem) {

            Appointment app = (Appointment) e.getItem();
			
			

            if (app.getContacts().size() == 0)
                return;
            if (app.getContacts().get(0).getId().startsWith("DBMS")) {

                java.awt.Image img = null;

                try {
                    
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../DBMS.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
                   
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
            } else if (app.getContacts().get(0).getId().startsWith("DC")) {

                java.awt.Image img = null;

                try {
                
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../DC.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
                    
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
            } else if (app.getContacts().get(0).getId().startsWith("JAVA")) {

                java.awt.Image img = null;

                try {
                 
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../JAVA.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
       
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop()+20);

                } catch (IOException ioe) {
                }
            } else if (app.getContacts().get(0).getId().startsWith("OS")) {

                java.awt.Image img = null;

                try {
               
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../OS.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
               
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
            }
			else if (app.getContacts().get(0).getId().startsWith("LA")) {

                java.awt.Image img = null;

                try {
                 
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../LA.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
                   
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
            }
			else if (app.getContacts().get(0).getId().startsWith("UNIX")) {

                java.awt.Image img = null;

                try {
                    
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../UNIX.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
               
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
            }else if (app.getContacts().get(0).getId().startsWith("REST")) {

                java.awt.Image img = null;

                try {
                
                    InputStream is = new BufferedInputStream(
                            new FileInputStream("../JAVA.png"));
                    img = ImageIO.read(is);

                    com.mindfusion.common.Rectangle r = e.getBounds();
                    AwtImage awtImage = new AwtImage(img);
                    
                    e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop() + 20);

                } catch (IOException ioe) {
                }
			}
        }
    }

    protected void onItemCreated(ItemEvent e) {
        Appointment item = (Appointment)e.getItem();
        String teacherName = teachers.getSelectedItem();
        for(Contact c:calendar.getSchedule().getContacts()) {
            if (c.getName().equals(teacherName)) {
                item.getContacts().add(calendar.getContacts().get(c.getId()));

            }
        }
        item.setHeaderText(teacherName);

    }

    protected void onCalendarItemCreating(ItemConfirmEvent e)
    {
        DateTime start = e.getItem().getStartTime();
        DateTime end = e.getItem().getEndTime();


        if(start.getDayOfWeek() == 0 || end.getDayOfWeek() == 0)
        {
            JOptionPane.showMessageDialog(this, "No Classes on Sunday!");
            e.setConfirm(false);
        }
    }

    public static void main(String[] args)
    {
		
		 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               
                try {
                    window = new MainWindow();
                    window.setVisible(true);
                }
                catch (Exception exp) {
                }
            }
        });
    }
	
	
	public void windowActivated(WindowEvent arg0) {  }	  
	public void windowClosed(WindowEvent arg0) {  
    System.out.println("closed"); 	
	}  
	public void windowClosing(WindowEvent arg0) {  
    
	TrayIcon t=new TrayIcon(Toolkit.getDefaultToolkit().getImage("../ICON.png"));
	
		 t.setToolTip("PLANNER");
		 t.addMouseListener(new MouseAdapter() {
		 public void mouseClicked(MouseEvent e)
		 {
			try {
                   
					window.setVisible(true);
					SystemTray.getSystemTray().remove(t);
                }
                catch (Exception exp) {
                }
		 }
		 });
		 
		 
		 try{
			 SystemTray.getSystemTray().add(t);
		 }catch(Exception e)
		 {}	
		 
		 }  
public void windowDeactivated(WindowEvent arg0) {  
     
}  
public void windowDeiconified(WindowEvent arg0) {  
    
}  
public void windowIconified(WindowEvent arg0) {  
     
}  
public void windowOpened(WindowEvent arg0) {  
    
}
}




