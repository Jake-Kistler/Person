import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PersonGUI extends JFrame implements ActionListener
{
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String currentFileName;
    private JList<String> peopleList;
    private boolean isSaved = false;

    ArrayList<Person> people = new ArrayList<>();



    JMenuItem saveMenuItem = new JMenuItem("Save");
    JMenuItem saveAsMenuItem = new JMenuItem("Save As");
    JMenuItem openMenuItem = new JMenuItem("Open");

    public PersonGUI()
    {

        // Set up the main frame
        setTitle("Person manager");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up the Jlist to display user creations
        DefaultListModel<String> listModel = new DefaultListModel<>();
        peopleList = new JList<>(listModel);
        JScrollPane scrollPane1 = new JScrollPane(peopleList);
        add(scrollPane1,BorderLayout.CENTER);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                if(!isSaved)
                {
                    int response = JOptionPane.showConfirmDialog(PersonGUI.this,
                            "Do you want to save your changes before closing?",
                            "Unsaved Changes",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION)
                    {
                        isSaved = true;
                        dispose();
                    }
                    else if(response == JOptionPane.NO_OPTION)
                    {
                        dispose();
                    }
                    else
                    {
                        dispose();
                    }
                }
            }
        });

        // Set up the text area
        textArea = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(textArea);
        add(scrollPane2, BorderLayout.CENTER);

        // Set up the file menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // New
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newFile();

            }
        });
        fileMenu.add(newMenuItem);

        // Save
        saveMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveFile();
            }
        });
        fileMenu.add(saveMenuItem);

        // Save As

        saveAsMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveFileAs();
            }
        });
        fileMenu.add(saveAsMenuItem);

        // Open
        openMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                openFile();
            }
        });
        fileMenu.add(openMenuItem);

        // Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        setJMenuBar(menuBar);

        // Set up the file chooser
        fileChooser = new JFileChooser();

        // Show the frame
        setVisible(true);

        // Display "Waiting for data"
        textArea.setText("Waiting for data");

        JList<String> peopleList = new JList<String>();

    }

    // New file
    private void newFile()
    {
        currentFileName = null;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
        AddWindow newWindow = new AddWindow(listModel, saveMenuItem, saveAsMenuItem);
        newWindow.setVisible(true);
        textArea.setText("");
    }


    // Save file
    private void saveFile()
    {
        if (currentFileName == null)
        {
            saveFileAs();
            writeToFile(people,currentFileName);
        }
        else
        {
            try
            {
                FileWriter writer = new FileWriter(currentFileName);
                writer.write(textArea.getText());
                writeToFile(people,currentFileName);
                writer.close();
                isSaved = true;
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    // Save file as
    private void saveFileAs()
    {
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            currentFileName = fileChooser.getSelectedFile().getAbsolutePath();
            saveFile();
        }
    }

    private void openFile()
    {
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try
            {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null)
                {
                    sb.append(line).append("\n");
                }
                textArea.setText(sb.toString());
                bufferedReader.close();
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(ArrayList<Person> people, String fileName)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write each person's data to the file
            for (Person person : people)
            {
                bufferedWriter.write(person.toString());
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
            fileWriter.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

    }


    public class AddWindow extends JFrame
    {
        private DefaultListModel<String> listModel;
        JTextField firstNameField;
        JTextField lastNameField;
        JTextField birthdayField;
        JTextField birthmonthField;
        JTextField birthyearField;
        JTextField govIDField;
        JTextField studentIDField;
        JButton addButton;
        JMenuItem saveMenuItem;
        JMenuItem saveAsMenuItem;

        public AddWindow(DefaultListModel<String> listModel, JMenuItem saveMenuItem, JMenuItem saveAsMenuItem)
        {
            this.listModel = listModel;
            this.saveMenuItem = saveMenuItem;
            this.saveAsMenuItem = saveAsMenuItem;

            saveAsMenuItem.setEnabled(false);
            saveMenuItem.setEnabled(false);

            setTitle("Add Person");
            setSize(500, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Set up the input fields
            JPanel inputPanel = new JPanel(new GridLayout(7, 2));
            inputPanel.add(new JLabel("First name:"));
            firstNameField = new JTextField();
            inputPanel.add(firstNameField);

            inputPanel.add(new JLabel("Last name:"));
            lastNameField = new JTextField();
            inputPanel.add(lastNameField);

            inputPanel.add(new JLabel("Birthday:"));
            birthdayField = new JTextField();
            inputPanel.add(birthdayField);

            inputPanel.add(new JLabel("Birth month:"));
            birthmonthField = new JTextField();
            inputPanel.add(birthmonthField);

            inputPanel.add(new JLabel("Birth year:"));
            birthyearField = new JTextField();
            inputPanel.add(birthyearField);

            inputPanel.add(new JLabel("Government ID:"));
            govIDField = new JTextField();
            inputPanel.add(govIDField);

            inputPanel.add(new JLabel("Student ID:"));
            studentIDField = new JTextField();
            inputPanel.add(studentIDField);

            add(inputPanel, BorderLayout.CENTER);

            // Set up the add button
            addButton = new JButton("Add");

            addButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    addPerson();
                }
            });

            add(addButton, BorderLayout.SOUTH);

            addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosed(WindowEvent e)
                {
                    saveAsMenuItem.setEnabled(true);
                    saveMenuItem.setEnabled(true);
                }
            });
        }

        private void addPerson()
        {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String birthDayText = birthdayField.getText();
            String birthMonthText = birthmonthField.getText();
            String birthYearText = birthyearField.getText();
            String govID = govIDField.getText();
            String studentID = studentIDField.getText();

            // Check if required fields are empty
            if (firstName.isEmpty() || lastName.isEmpty() || birthMonthText.isEmpty() || birthDayText.isEmpty() || birthYearText.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please provide a full name as well as a birthday to contiune");
                return;
            }

            int birthMonth = Integer.parseInt(birthMonthText);
            int birthDay = Integer.parseInt(birthDayText);
            int birthYear = Integer.parseInt(birthYearText);

            OCCCDate dob = new OCCCDate(birthDay, birthMonth, birthYear);

            Person person = null;

            if (!govID.isEmpty() && studentID.isEmpty())
            {
                person = new RegisteredPerson(firstName, lastName, dob, govID);
            }
            else if (!govID.isEmpty() && !studentID.isEmpty())
            {
                person = new OCCCPerson(new RegisteredPerson(new Person(firstName, lastName, dob), govID), studentID);
            }
            else
            {
                person = new Person(firstName, lastName, dob);
            }

            people.add(person);

            JOptionPane.showMessageDialog(this, "Person added: " + person);
            listModel.addElement(person.toString());
            textArea.setText("");
            for(Person p : people)
            {
                textArea.append(p.toString() + "\n");
            }

            firstNameField.setText("");
            lastNameField.setText("");
            birthmonthField.setText("");
            birthdayField.setText("");
            birthyearField.setText("");
            govIDField.setText("");
            studentIDField.setText("");
        }


    }

    public static void main(String args[])
    {
        PersonGUI personGUI = new PersonGUI();
    }

}
