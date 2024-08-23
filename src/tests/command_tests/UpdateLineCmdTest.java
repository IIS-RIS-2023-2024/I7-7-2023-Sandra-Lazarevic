package tests.command_tests;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import geometry.Line;
import geometry.Point;
import command.UpdateLineCmd;

public class UpdateLineCmdTest {

    private Line oldLine;
    private Line newLine;
    private Line originalLine;
    private UpdateLineCmd updateLineCmdTest;

    @BeforeEach
    public void setUp() {
        Point oldStart = new Point(1, 1);
        Point oldEnd = new Point(2, 2);
        oldLine = new Line(oldStart, oldEnd, Color.RED);
        
        Point newStart = new Point(10, 10);
        Point newEnd = new Point(20, 20);
        newLine = new Line(newStart, newEnd, Color.BLUE);

        originalLine = (Line) oldLine.clone();
        updateLineCmdTest = new UpdateLineCmd(oldLine, newLine);
    }

    @Test
    public void testExecute() {
        updateLineCmdTest.execute();

        assertEquals(newLine.getStartPoint().getX(), oldLine.getStartPoint().getX());
        assertEquals(newLine.getStartPoint().getY(), oldLine.getStartPoint().getY());
        assertEquals(newLine.getEndPoint().getX(), oldLine.getEndPoint().getX());
        assertEquals(newLine.getEndPoint().getY(), oldLine.getEndPoint().getY());
        assertEquals(newLine.getStartPoint().getColor(), oldLine.getEndPoint().getColor());

    }

    @Test
    public void testUnexecute() {
        updateLineCmdTest.execute();
        updateLineCmdTest.unexecute();

        assertEquals(originalLine.getStartPoint().getX(), oldLine.getStartPoint().getX());
        assertEquals(originalLine.getStartPoint().getY(), oldLine.getStartPoint().getY());
        assertEquals(originalLine.getEndPoint().getX(), oldLine.getEndPoint().getX());
        assertEquals(originalLine.getEndPoint().getY(), oldLine.getEndPoint().getY());
        assertEquals(newLine.getStartPoint().getColor(), oldLine.getEndPoint().getColor());
    }

}
